package kobdig.service;

import kobdig.communication.Sender;
import kobdig.mongo.collections.*;
import kobdig.mongo.repository.*;
import kobdig.agent.Agent;
import kobdig.sql.repository.IndicatorOneRepository;
import kobdig.sql.repository.IndicatorTwoRepository;
import kobdig.sql.tables.IndicatorOne;
import kobdig.sql.tables.IndicatorTwo;
import kobdig.urbanSimulation.EntitiesCreator;
import kobdig.urbanSimulation.entities.agents.AbstractAgent;
import kobdig.urbanSimulation.entities.agents.Household;
import kobdig.urbanSimulation.entities.agents.Investor;
import kobdig.urbanSimulation.entities.agents.Promoter;
import kobdig.urbanSimulation.entities.environement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Meili on 20/06/16.
 */
@Component
public class Simulation {

    public static final double INCOME_GAP = 0.3;
    public static final String NETWORK = "network";
    private int iterations = 0;
    private int actualIteration = 0;
    public static final String EQUIPMENT = "equipment";
    protected static Agent investorAgent;

    @Autowired
    private EntitiesCreator builder;

    @Autowired
    private IndicatorOneRepository indicatorOneRepository;

    @Autowired
    private IndicatorTwoRepository indicatorTwoRepository;

    @Autowired
    private InvestorMongoRepository investorMongoRepository;

    @Autowired
    private PromoterMongoRepository promoterMongoRepository;

    @Autowired
    private LandMongoRepository landMongoRepository;

    @Autowired
    private PropertyMongoRepository propertyMongoRepository;

    @Autowired
    private HouseholdMongoRepository householdMongoRepository;

    @Autowired
    private Sender sender;

    /** Execution delay in milliseconds */
    private volatile int executionDelay = 10;

    /** Animation thread. */
    private Thread thread;

    /** A flag for controlling the animation thread */
    private volatile boolean running = false;

    /**
     * Writes the resultant data indicators in the database
     * @throws SQLException
     */
    public void writeIndicators(EntitiesCreator entitiesCreator, int time) throws SQLException{
        double countRent = 0.0;
        double countSale = 0.0;
        for (AdministrativeDivision division : entitiesCreator.getDivisions()) {
            if (division != null) {
                double rentInDivision = 0.0;
                double saleInDivision = 0.0;
                for (Property property : division.getProperties()) {
                    if (property.getState().equals(Property.OCCUPIED)) {
                        countSale++;
                        saleInDivision++;
                    } else if (property.getState().equals(Property.RENTED)) {
                        countRent++;
                        saleInDivision++;
                    }
                }
                division.setOnSaleProperties(saleInDivision);
                division.setRentedProperties(rentInDivision);
            }
        }
        for (AdministrativeDivision division : entitiesCreator.getDivisions()) {
            if (division != null) {
                double sale = (countSale > 0.0) ? division.getOnSaleProperties() / countSale : 0;
                double rent = (countRent > 0.0) ? division.getRentedProperties() / countRent : 0;
                double si = Math.abs(sale - rent) / 2.0;
                indicatorTwoRepository.save(new IndicatorTwo(time, Integer.parseInt(division.getId()), si));
            }
        }
        double rop = ((countRent + countSale) > 0.0)? countRent/(countRent + countSale): 0.0;
        indicatorOneRepository.save(new IndicatorOne(time, rop));

    }

    /**
     * Writes the resultant data in the database
     * @throws SQLException
     */
    public void writeResults(EntitiesCreator entitiesCreator, int time) throws SQLException {
        for (AdministrativeDivision division : entitiesCreator.getDivisions()) {
            if (division != null) {
                for (Property property : division.getProperties()) {
                    propertyMongoRepository.save(new PropertyMongo(builder.getId(), time, property));
                }
                for(Land l : division.getLands()){
                    landMongoRepository.save(new LandMongo(builder.getId(), time, l));
                }
            }
        }
        for(AbstractAgent h : entitiesCreator.getAgents()){
            if(h instanceof Household){
                householdMongoRepository.save(new HouseholdMongo(builder.getId(), time,(Household) h));
            }
            else if(h instanceof Promoter){
                promoterMongoRepository.save(new PromoterMongo(builder.getId(), time, (Promoter) h));
            }
        }
        for(Investor i : entitiesCreator.getInvestors()){
            investorMongoRepository.save(new InvestorMongo(builder.getId(), time, i));
        }
    }

    public synchronized void start() {
        if (running) {
            throw new IllegalStateException("Animation is already running.");
        }

        // the reason we do not inherit from Runnable is that we do not want to
        // expose the void run() method to the outside world. We want to well
        // encapsulate the whole idea of a thread.
        // thread cannot be restarted so we need to always create a new one
        thread = new Thread() {
            @Override
            public void run() {

                while (true) {
                    synchronized (this) {
                        while (!running) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    try {
                        synchronized (this) {
                            Thread.sleep(executionDelay);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        simulate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    builder.setTime(builder.getTime()+1);
                }
            }
        };

        // start the thread
        thread.start();
        // set the flag
        running = true;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void stop() {
        if (!running) {
            throw new IllegalStateException("Animation is stopped.");
        }
        running = false;
        builder.setTime(0);
    }

    public void simulate() throws IOException {
        /*if (builder.getTime() == 0) {

            try {
                writeIndicators(builder, 0);
                writeResults(builder, 0);
                //builder.getIdManager()[0] = 100 + 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else*/
        if (builder.getTime() >= builder.getNumSim()) {
            System.err.println("SIMULATION FINISHED");
            running = false;
            sender.send("extract", Integer.toString(builder.getId()));
        } else {
            System.err.println("STEP " + (builder.getTime()+1) + "/" + builder.getNumSim());
            int occuped = 0;
            int rented = 0;
            int forsale = 0;
            int landsize = 0;
            int forrent = 0;
            for (int i = 0; i < builder.getDivisions().length; i++) {
                if (builder.getDivisions()[i] != null) {
                    ArrayList<Land> landDiv = builder.getDivisions()[i].getLands();
                    for (Land land : landDiv) land.step(builder.getTime());
                    for (Property property : builder.getDivisions()[i].getProperties())
                        property.step(builder.getTime());
                    occuped += builder.getDivisions()[i].getPropertiesOccuped();
                    rented += builder.getDivisions()[i].getPropertiesRented();
                    forsale += builder.getDivisions()[i].getPropertiesForSale();
                    forrent += builder.getDivisions()[i].getPropertiesForRent();
                    landsize += builder.getDivisions()[i].getLands().size();
                }
            }
            for (AbstractAgent agent : builder.getAgents()) {
                agent.agentUpdateBeliefs(builder, builder.getTime());
                agent.agentIntentionsStep(builder);
            }

            for (Investor investor : builder.getInvestors()) {
                investor.agentUpdateBeliefs(builder, builder.getTime());
                investor.agentIntentionsStep(builder);
            }


            try {
                writeIndicators(builder, builder.getTime());
                writeResults(builder, builder.getTime());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("occuped : " + occuped + " for rent " + forrent + " rented : " + rented + " for sale : " + forsale);
            System.out.println("land size : " + landsize);

        }
    }
}
