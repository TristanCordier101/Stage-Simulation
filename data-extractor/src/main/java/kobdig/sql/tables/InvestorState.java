package kobdig.sql.tables;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "investor_state")
public class InvestorState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int gid;

    @Column(name = "step")
    private int step;

    @Column(name = "idhousehold")
    private String idhousehold;

    @Column(name = "id")
    private String id;

    @Column(name = "idsimulation")
    private int idsimulation;

    @Column(name = "purchasingpower")
    private double purchasingpower;

    @Column(name = "monthlyincome")
    private double monthlyincome;

    @Column(name = "investdegree")
    private double investdegree;

    @Column(name = "speculate")
    private double speculate;

    @Column(name = "currentrent")
    private double currentrent;

    public int getGid() {
        return gid;
    }

    public int getStep() {
        return step;
    }

    public String getIdhousehold() {
        return idhousehold;
    }

    public String getId() {
        return id;
    }

    public int getIdsimulation() {
        return idsimulation;
    }

    public double getPurchasingpower() {
        return purchasingpower;
    }

    public double getMonthlyincome() {
        return monthlyincome;
    }

    public double getInvestdegree() {
        return investdegree;
    }

    public double getSpeculate() {
        return speculate;
    }

    public double getCurrentrent() {
        return currentrent;
    }

    public InvestorState(){

    }

    public InvestorState(int step, String idhousehold, String id, int idsimulation, double purchasingpower, double monthlyincome, double investdegree, double speculate, double currentrent) {
        this.step = step;
        this.idhousehold = idhousehold;
        this.id = id;
        this.idsimulation = idsimulation;
        this.purchasingpower = purchasingpower;
        this.monthlyincome = monthlyincome;
        this.investdegree = investdegree;
        this.speculate = speculate;
        this.currentrent = currentrent;
    }
}