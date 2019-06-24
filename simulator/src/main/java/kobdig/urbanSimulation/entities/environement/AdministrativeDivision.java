package kobdig.urbanSimulation.entities.environement;

import org.postgis.PGgeometry;

import java.util.ArrayList;

/**
 * Created by Meili on 7/25/16.
 */
public class AdministrativeDivision {
    private String id;
    private int code;
    private ArrayList<Land> lands;
    private ArrayList<Property> properties;
    private ArrayList<Equipment> equipments;
    private ArrayList<TransportNetwork> networks;
    private PGgeometry geom;
    private double onSaleProperties;
    private double rentedProperties;

    public AdministrativeDivision(String id, int code, PGgeometry geom){
        this.id = id;
        this.code = code;
        this. geom = geom;
        this.lands = new ArrayList<>();
        this.networks = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.equipments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public double getOnSaleProperties() {
        return onSaleProperties;
    }

    public void setOnSaleProperties(double onSaleProperties) {
        this.onSaleProperties = onSaleProperties;
    }

    public double getRentedProperties() {
        return rentedProperties;
    }

    public void setRentedProperties(double rentedProperties) {
        this.rentedProperties = rentedProperties;
    }

    public ArrayList<Land> getLands() {
        return lands;
    }

    public ArrayList<Equipment> getEquipments(){
        return equipments;
    }

    public void addEquipement(Equipment equip){
        equipments.add(equip);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property prop){
        properties.add(prop);
    }

    public ArrayList<TransportNetwork> getNetworks() {
        return networks;
    }

    public void addNetwork(TransportNetwork network){
        networks.add(network);
    }

    public void addLand(Land land) {
        lands.add(land);
    }

    public int getPropertiesOccuped(){
        int res = 0;
        for(Property p : properties){
            switch (p.getState()){
                case Property.OCCUPIED:
                    res++;
                    break;

            }
        }
        return res;
    }

    public int getPropertiesRented(){
        int res = 0;
        for(Property p : properties){
            switch (p.getState()){
                case Property.RENTED:
                    res++;
                    break;

            }
        }
        return res;
    }

    public int getPropertiesForSale(){
        int res = 0;
        for(Property p : properties){
            switch (p.getState()){
                case Property.FOR_SALE:
                    res++;
                    break;

            }
        }
        return res;
    }

    public int getPropertiesForRent(){
        int res = 0;
        for(Property p : properties){
            switch (p.getState()){
                case Property.SEEKING_TENANT:
                    res++;
                    break;

            }
        }
        return res;
    }
}
