package kobdig.sql.tables;
import kobdig.urbanSimulation.entities.agents.Investor;

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

    public InvestorState(){

    }

    public InvestorState(int idSimulation, int step, Investor i){
        this.idsimulation = idSimulation;
        this.step = step;
        this.id = i.getId();
        this.purchasingpower = i.getCurrentPurchasingPower();
        this.monthlyincome = i.getCurrentNetMonthlyIncome();
        this.speculate = i.getSpeculate();
        this.currentrent = i.getCurrentRent();
        this.investdegree = i .getInvestDegree();
        try {
            this.idhousehold = i.getHousehold().getId();
        }
        catch(NullPointerException e){
            this.idhousehold = "none";
        }
    }
}