package kobdig.controller;

import kobdig.eventbus.input.SimulationMessage;
import kobdig.eventbus.input.TabSimulationMessage;
import kobdig.eventbus.input.BatchSimulationMessage;
import kobdig.mongo.collections.ConfigurationMongo;
import kobdig.mongo.repository.*;
import kobdig.service.Simulation;
import kobdig.sql.repository.ConfigurationRepository;
import kobdig.sql.tables.Configuration;
import kobdig.sql.repository.PropertyRepository;
import kobdig.urbanSimulation.EntitiesCreator;
import kobdig.urbanSimulation.utils.SimulationLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WebController {

    private List<SimulationMessage> simulationMessages = new ArrayList<>();

    @Autowired
    private EntitiesCreator entitiesCreator;

    @Autowired
    public Simulation simulation;

    @Autowired
    public PropertyRepository propertyRepository;

    @Autowired
    public ConfigurationMongoRepository configurationMongoRepository;

    @Autowired
    public ConfigurationRepository configurationRepository;

    @Autowired
    public SimulationLogging log;

    @PostMapping("/state")
    public ResponseEntity<Void> startSimulation(@RequestBody SimulationMessage message) {
        Date time = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.YEAR_FIELD);
        String date = shortDateFormat.format(time);

        int idSimulation = 0;
        if (message.getStorageType() == 0) {
            while (configurationMongoRepository.findByidSimulation(idSimulation) != null) {
                idSimulation++;
            }
        } else {
            while (configurationRepository.findByidSimulation(idSimulation) != null) {
                idSimulation++;
            }
        }


        if (!simulation.isRunning()) {
            entitiesCreator.setNumSim(message.getNum());
            entitiesCreator.setNbrInvestor(message.getNbrInvestor());
            entitiesCreator.setNbrPromoter(message.getNbrPromoter());
            entitiesCreator.setNbrHousehold(message.getNbrHousehold());
            entitiesCreator.setId(idSimulation);
            entitiesCreator.setListOfEquipment(message.getListOfEquipment());
            entitiesCreator.setListOfTransport(message.getListOfTransport());
            entitiesCreator.setFileHousehold(message.getFileHousehold());
            entitiesCreator.setFileInvestor(message.getFileInvestor());
            entitiesCreator.setFilePromoter(message.getFilePromoter());
            entitiesCreator.createAll();
            if (message.getStorageType() == 0) {
                configurationMongoRepository.save(new ConfigurationMongo(date, message.getNum(), message.getNbrHousehold(), message.getNbrPromoter(), message.getNbrInvestor(), idSimulation, message.getFileHousehold(), message.getFileInvestor(), message.getFilePromoter(), message.getListOfEquipment(), message.getListOfTransport()));
            } else {
                configurationRepository.save(new Configuration(message.getNum(), message.getNbrHousehold(), message.getNbrPromoter(), message.getNbrInvestor(), message.getFileHousehold(), message.getFileInvestor(), message.getFilePromoter(), idSimulation, message.getListOfEquipment().toString(), message.getListOfTransport().toString(), time));

            }
            simulation.start(message.getStorageType());
            while (simulation.isRunning()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Date time2 = new Date();
            log.writeData("------------------------------------------------------------------");
            log.writeData("SIMULATION DU " + date + " NUMERO " + idSimulation);
            log.writeData("terminée à " + shortDateFormat.format(time2));
            log.writeData("------------------------------------------------------------------");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/statetab")
    public ResponseEntity<Void> startTabSimulation(@RequestBody TabSimulationMessage tabMessage) {

        Date time = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.YEAR_FIELD);
        String date = shortDateFormat.format(time);

        for (SimulationMessage simulationMessage : tabMessage.getSimulationMessageList()) {
            int idSimulationBis = 0;
            if (simulationMessage.getStorageType() == 0) {
                while (configurationMongoRepository.findByidSimulation(idSimulationBis) != null) {
                    idSimulationBis++;
                }
            } else {
                while (configurationRepository.findByidSimulation(idSimulationBis) != null) {
                    idSimulationBis++;
                }
            }
            entitiesCreator.setNumSim(simulationMessage.getNum());
            entitiesCreator.setNbrInvestor(simulationMessage.getNbrInvestor());
            entitiesCreator.setNbrPromoter(simulationMessage.getNbrPromoter());
            entitiesCreator.setNbrHousehold(simulationMessage.getNbrHousehold());
            entitiesCreator.setId(idSimulationBis);
            entitiesCreator.setListOfEquipment(simulationMessage.getListOfEquipment());
            entitiesCreator.setListOfTransport(simulationMessage.getListOfTransport());
            entitiesCreator.setFileHousehold(simulationMessage.getFileHousehold());
            entitiesCreator.setFileInvestor(simulationMessage.getFileInvestor());
            entitiesCreator.setFilePromoter(simulationMessage.getFilePromoter());
            entitiesCreator.createAll();
            if (simulationMessage.getStorageType() == 0) {
                configurationMongoRepository.save(new ConfigurationMongo(date, simulationMessage.getNum(), simulationMessage.getNbrHousehold(), simulationMessage.getNbrPromoter(), simulationMessage.getNbrInvestor(), idSimulationBis, simulationMessage.getFileHousehold(), simulationMessage.getFileInvestor(), simulationMessage.getFilePromoter(), simulationMessage.getListOfEquipment(), simulationMessage.getListOfTransport()));
            } else {
                configurationRepository.save(new Configuration(simulationMessage.getNum(), simulationMessage.getNbrHousehold(), simulationMessage.getNbrPromoter(), simulationMessage.getNbrInvestor(), simulationMessage.getFileHousehold(), simulationMessage.getFileInvestor(), simulationMessage.getFilePromoter(), idSimulationBis, simulationMessage.getListOfEquipment().toString(), simulationMessage.getListOfTransport().toString(), time));

            }
            simulation.start(simulationMessage.getStorageType());
            while (simulation.isRunning()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Date time2 = new Date();
            log.writeData("------------------------------------------------------------------");
            log.writeData("SIMULATION DU " + date + " NUMERO " + idSimulationBis);
            log.writeData("terminée à " + shortDateFormat.format(time2));
            log.writeData("------------------------------------------------------------------");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/stop")
    public ResponseEntity<Void> stopSimulation() {

        simulation.stop();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> batchSimulation(@RequestBody BatchSimulationMessage message) {

        int idSimulation = 0;
        if (message.getStorageType() == 0) {
            while (configurationMongoRepository.findByidSimulation(idSimulation) != null) {
                idSimulation++;
            }
        } else {
            while (configurationRepository.findByidSimulation(idSimulation) != null) {
                idSimulation++;
            }
        }

        List<Integer> numRange = message.getNumRange(), householdRange= message.getNbrHouseholdRange(), investorRange=message.getNbrInvestorRange(), promoterRange=message.getNbrPromoterRange();

        for (int num = numRange.get(0); num <= numRange.get(numRange.size()-1) ; num += message.getNumStep()){
            for(int nbrHousehold = householdRange.get(0); nbrHousehold <= householdRange.get(householdRange.size()-1); nbrHousehold += message.getNbrHouseholdStep()){
                for(int nbrInvestor = investorRange.get(0);nbrInvestor <= investorRange.get(investorRange.size()-1); nbrInvestor += message.getNbrInvestorStep()){
                    for(int nbrPromoter = promoterRange.get(0); nbrPromoter <= promoterRange.get(promoterRange.size()-1); nbrPromoter += message.getNbrPromoterStep()){
                        Date time = new Date();
                        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.YEAR_FIELD);
                        String date = shortDateFormat.format(time);

                        entitiesCreator.setNumSim(num);
                        entitiesCreator.setNbrInvestor(nbrInvestor);
                        entitiesCreator.setNbrPromoter(nbrPromoter);
                        entitiesCreator.setNbrHousehold(nbrHousehold);
                        entitiesCreator.setId(idSimulation);
                        entitiesCreator.setListOfEquipment(message.getListOfEquipment());
                        entitiesCreator.setListOfTransport(message.getListOfTransport());
                        entitiesCreator.setFileHousehold(message.getFileHousehold());
                        entitiesCreator.setFileInvestor(message.getFileInvestor());
                        entitiesCreator.setFilePromoter(message.getFilePromoter());
                        entitiesCreator.createAll();
                        if (message.getStorageType() == 0) {
                            configurationMongoRepository.save(new ConfigurationMongo(date, num, nbrHousehold, nbrPromoter, nbrInvestor, idSimulation, message.getFileHousehold(), message.getFileInvestor(), message.getFilePromoter(), message.getListOfEquipment(), message.getListOfTransport()));
                        } else {
                            configurationRepository.save(new Configuration(num, nbrHousehold, nbrPromoter, nbrInvestor, message.getFileHousehold(), message.getFileInvestor(), message.getFilePromoter(), idSimulation, message.getListOfEquipment().toString(), message.getListOfTransport().toString(), time));

                        }
                        simulation.start(message.getStorageType());
                        while (simulation.isRunning()) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Date time2 = new Date();
                        log.writeData("------------------------------------------------------------------");
                        log.writeData("SIMULATION DU " + date + " NUMERO " + idSimulation);
                        log.writeData("terminée à " + shortDateFormat.format(time2));
                        log.writeData("------------------------------------------------------------------");

                        idSimulation++;
                    }
                }
            }
        }


        return new ResponseEntity<>(HttpStatus.OK);


    }
}