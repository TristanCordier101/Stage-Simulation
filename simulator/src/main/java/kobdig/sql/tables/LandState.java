package kobdig.sql.tables;

import kobdig.urbanSimulation.entities.environement.Land;

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

    public LandState(){

    }

    public LandState(int idSimulation, int step, Land l) {
        this.idsimulation = idSimulation;
        this.utility = l.getUtility();
        this.id = l.getId();
        this.step = step;
        this.price = l.getPrice();
    }
}