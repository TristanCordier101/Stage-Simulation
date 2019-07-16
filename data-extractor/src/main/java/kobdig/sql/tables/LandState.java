package kobdig.sql.tables;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "land_state")
public class LandState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int gid;

    @Column(name = "step")
    private int step;

    @Column(name = "idsimulation")
    private int idsimulation;

    @Column(name = "id")
    private String id;

    @Column(name = "price")
    private double price;

    @Column(name = "utility")
    private double utility;

    @Column(name = "geom")
    private String geom;

    public int getGid() {
        return gid;
    }

    public int getStep() {
        return step;
    }

    public int getIdsimulation() {
        return idsimulation;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getUtility() {
        return utility;
    }

    public String getGeom() {
        return geom;
    }

    public LandState(){

    }

    public LandState(int step, int idsimulation, String id, double price, double utility) {
        this.step = step;
        this.idsimulation = idsimulation;
        this.id = id;
        this.price = price;
        this.utility = utility;
    }
}