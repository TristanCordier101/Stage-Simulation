package kobdig.sql.tables;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "promoter_state")
public class PromoterState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int gid;

    @Column(name = "step")
    private int step;

    @Column(name = "id")
    private String id;

    @Column(name = "idsimulation")
    private int idsimulation;

    @Column(name = "purchasingpower")
    private double purchasingpower;

    @Column(name = "landssize")
    private double landssize;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdsimulation() {
        return idsimulation;
    }

    public void setIdsimulation(int idsimulation) {
        this.idsimulation = idsimulation;
    }

    public double getPurchasingpower() {
        return purchasingpower;
    }

    public void setPurchasingpower(double purchasingpower) {
        this.purchasingpower = purchasingpower;
    }

    public double getLandssize() {
        return landssize;
    }

    public void setLandssize(double landssize) {
        this.landssize = landssize;
    }

    public PromoterState(){

    }

    public PromoterState(int step, String id, int idsimulation, double purchasingpower, double landssize) {
        this.step = step;
        this.id = id;
        this.idsimulation = idsimulation;
        this.purchasingpower = purchasingpower;
        this.landssize = landssize;
    }
}
