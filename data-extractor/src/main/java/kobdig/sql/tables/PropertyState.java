package kobdig.sql.tables;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "property_state")
public class PropertyState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int gid;

    @Column(name = "step")
    private int step;

    @Column(name = "idproperty")
    private String idproperty;

    @Column(name = "idsimulation")
    private int idsimulation;

    @Column(name = "currentprice")
    private double currentprice;

    @Column(name = "currentcapitalizedrent")
    private double currentcapitalizedrent;

    @Column(name = "currentpotentialrent")
    private double currentpotentialrent;

    @Column(name = "currentvalue")
    private double currentvalue;

    @Column(name = "previousprice")
    private double previousprice;

    @Column(name = "previouscapitalizedrent")
    private double previouscapitalizedrent;

    @Column(name = "previouspotentialrent")
    private double previouspotentialrent;

    @Column(name = "previousvalue")
    private double previousvalue;

    @Column(name = "state")
    private String state;

    @Column(name = "idland")
    private int idLand;

    @Column(name = "geom")
    private String geom;


    public int getGid() {
        return gid;
    }

    public int getStep() {
        return step;
    }

    public String getIdproperty() {
        return idproperty;
    }

    public int getIdsimulation() {
        return idsimulation;
    }

    public double getCurrentprice() {
        return currentprice;
    }

    public double getCurrentcapitalizedrent() {
        return currentcapitalizedrent;
    }

    public double getCurrentpotentialrent() {
        return currentpotentialrent;
    }

    public double getCurrentvalue() {
        return currentvalue;
    }

    public double getPreviousprice() {
        return previousprice;
    }

    public double getPreviouscapitalizedrent() {
        return previouscapitalizedrent;
    }

    public double getPreviouspotentialrent() {
        return previouspotentialrent;
    }

    public double getPreviousvalue() {
        return previousvalue;
    }

    public String getState() {
        return state;
    }

    public int getIdLand() {
        return idLand;
    }

    public String getGeom() {
        return geom;
    }

    public PropertyState(){

    }

    public PropertyState(int step, String idproperty, int idsimulation, double currentprice, double currentcapitalizedrent, double currentpotentialrent, double currentvalue, double previousprice, double previouscapitalizedrent, double previouspotentialrent, double previousvalue, String state, int idLand, String geom) {
        this.step = step;
        this.idproperty = idproperty;
        this.idsimulation = idsimulation;
        this.currentprice = currentprice;
        this.currentcapitalizedrent = currentcapitalizedrent;
        this.currentpotentialrent = currentpotentialrent;
        this.currentvalue = currentvalue;
        this.previousprice = previousprice;
        this.previouscapitalizedrent = previouscapitalizedrent;
        this.previouspotentialrent = previouspotentialrent;
        this.previousvalue = previousvalue;
        this.state = state;
        this.idLand = idLand;
        this.geom = geom;
    }
}
