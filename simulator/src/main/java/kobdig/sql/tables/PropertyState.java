package kobdig.sql.tables;

import kobdig.urbanSimulation.entities.environement.Property;
import org.postgis.Geometry;
import org.postgis.PGgeometry;

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




    public PropertyState(){

    }

    public PropertyState(int idSimulation, int step, Property p){
        this.idsimulation = idSimulation;
        this.step = step;
        this.idproperty = p.getId();
        this.currentprice = p.getCurrentPrice();
        this.previousprice = p.getPreviousPrice();
        this.currentcapitalizedrent = p.getCurrentCapitalizedRent();
        this.previouscapitalizedrent = p.getPreviousCapitalizedRent();
        this.currentvalue = p.getCurrentValue();
        this.previousvalue = p.getPreviousValue();
        this.currentpotentialrent = p.getCurrentPotentialRent();
        this.previouspotentialrent = p.getPreviousPotentialRent();
        this.state = p.getState();
        this.idLand=-1;
        if(p.getLand().getId().equals("none")){
            this.idLand=-1;
        }else{
            this.idLand =Integer.parseInt(p.getLand().getId());
        }
    }
}
