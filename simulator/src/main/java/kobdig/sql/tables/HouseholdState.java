package kobdig.sql.tables;

import kobdig.urbanSimulation.entities.agents.Household;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "household_state")
public class HouseholdState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int gid;

    @Column(name = "step")
    private int step;

    @Column(name = "idproperty")
    private String idproperty;

    @Column(name = "id")
    private String id;

    @Column(name = "idsimulation")
    private int idsimulation;

    @Column(name = "rentableproperties")
    private double rentableproperties;

    @Column(name = "purchasingpower")
    private double purchasingpower;

    @Column(name = "netmonthlyincome")
    private double netmonthlyincome;

    public HouseholdState(){

    }

    public HouseholdState(int idSimulation, int step, Household h) {
        try {
            this.idproperty = h.getProperty().getId();
        } catch (NullPointerException e) {
            this.idproperty = "none";
        }
        this.step = step;
        this.purchasingpower = h.getCurrentPurchasingPower();
        this.id = h.getId();
        this.netmonthlyincome = h.getCurrentNetMonthlyIncome();
        this.idsimulation = idSimulation;
        this.rentableproperties = h.getRentableProperties().size();
    }
}