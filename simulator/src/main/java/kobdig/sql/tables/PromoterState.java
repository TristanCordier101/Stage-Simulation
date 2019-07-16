package kobdig.sql.tables;

import kobdig.urbanSimulation.entities.agents.Promoter;

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



    public PromoterState(){

    }

    public PromoterState(int sumlationId, int step, Promoter p){
        this.step = step;
        this.idsimulation = sumlationId;
        this.id = p.getId();
        this.purchasingpower = p.getPurchasingPower();
        this.landssize = p.getPurchasableLand().size();
    }
}
