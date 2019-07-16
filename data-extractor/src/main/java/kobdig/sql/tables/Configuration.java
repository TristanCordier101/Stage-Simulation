package kobdig.sql.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "configuration_state")
public class Configuration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gid")
    private int id;

    @Column(name = "num")
    private int num;

    @Column(name = "nbrhouseholds")
    private double nbrhouseholds;

    @Column(name = "nbrpromoters")
    private double nbrpromoters;

    @Column(name = "nbrinvestors")
    private double nbrinvestors;

    @Column(name = "filehousehold")
    private String filehousehold;

    @Column(name = "filepromoters")
    private String filepromoters;

    @Column(name = "fileinvestors")
    private String fileinvestors;

    @Column(name = "idsimulation")
    private double idsimulation;

    @Column(name = "listofequipement")
    private String listofequipement;

    @Column(name = "listofnetwork")
    private String listofnetwork;

    @Column(name = "time")
    private Date time;

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public double getNbrhouseholds() {
        return nbrhouseholds;
    }

    public double getNbrpromoters() {
        return nbrpromoters;
    }

    public double getNbrinvestors() {
        return nbrinvestors;
    }

    public String getFilehousehold() {
        return filehousehold;
    }

    public String getFilepromoters() {
        return filepromoters;
    }

    public String getFileinvestors() {
        return fileinvestors;
    }

    public double getIdsimulation() {
        return idsimulation;
    }

    public String getListofequipement() {
        return listofequipement;
    }

    public String getListofnetwork() {
        return listofnetwork;
    }

    public Date getTime() {
        return time;
    }

    public Configuration(){}

    public Configuration(int num, double nbrhouseholds, double nbrpromoters, double nbrinvestors, String filehousehold, String filepromoters, String fileinvestors, double idsimulation, String listofequipement, String listofnetwork, Date time) {
        this.num = num;
        this.nbrhouseholds = nbrhouseholds;
        this.nbrpromoters = nbrpromoters;
        this.nbrinvestors = nbrinvestors;
        this.filehousehold = filehousehold;
        this.filepromoters = filepromoters;
        this.fileinvestors = fileinvestors;
        this.idsimulation = idsimulation;
        this.listofequipement = listofequipement;
        this.listofnetwork = listofnetwork;
        this.time = time;
    }
}