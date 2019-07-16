package kobdig.sql.tables;

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


    public int getGid() {
        return gid;
    }

    public int getStep() {
        return step;
    }

    public String getIdproperty() {
        return idproperty;
    }

    public String getId() {
        return id;
    }

    public int getIdsimulation() {
        return idsimulation;
    }

    public double getRentableproperties() {
        return rentableproperties;
    }

    public double getPurchasingpower() {
        return purchasingpower;
    }

    public double getNetmonthlyincome() {
        return netmonthlyincome;
    }

    public HouseholdState(){

    }

    public HouseholdState(int step, String idproperty, String id, int idsimulation, double rentableproperties, double purchasingpower, double netmonthlyincome) {
        this.step = step;
        this.idproperty = idproperty;
        this.id = id;
        this.idsimulation = idsimulation;
        this.rentableproperties = rentableproperties;
        this.purchasingpower = purchasingpower;
        this.netmonthlyincome = netmonthlyincome;
    }
}