package kobdig.eventbus.input;

import java.util.ArrayList;
import java.util.List;

public class BatchSimulationMessage {
    private List<Integer> numRange = new ArrayList<>();
    private int numStep = 1;

    private int storageType = 1;

    private List<Integer> nbrHouseholdRange = new ArrayList<>();
    private int nbrHouseholdStep = 1;
    private List<Integer> nbrPromoterRange = new ArrayList<>();
    private int nbrPromoterStep = 1;
    private List<Integer> nbrInvestorRange = new ArrayList<>();
    private int nbrInvestorStep = 1;

    private List<Integer> listOfEquipment = new ArrayList<>();
    private List<Integer> listOfTransport = new ArrayList<>();

    private String fileHousehold;
    private String fileInvestor;
    private String filePromoter;


    public List<Integer> getNumRange() {
        if(numRange.isEmpty()) {
            numRange.add(10);
        }
        return numRange;
    }

    public int getNumStep() {
        if(numStep<=0){
            return 1;
        }
        return numStep;
    }

    public int getStorageType() {
        return storageType;
    }

    public List<Integer> getNbrHouseholdRange() {
        if(nbrHouseholdRange.isEmpty()){
            nbrHouseholdRange.add(50);
        }
        return nbrHouseholdRange;
    }

    public int getNbrHouseholdStep() {
        if(nbrHouseholdStep<=0){
            return 1;
        }
        return nbrHouseholdStep;
    }

    public List<Integer> getNbrPromoterRange() {
        if(nbrPromoterRange.isEmpty()) {
            nbrPromoterRange.add(50);
        }
        return nbrPromoterRange;
    }

    public int getNbrPromoterStep() {
        if(nbrPromoterStep<=0){
            return 1;
        }
        return nbrPromoterStep;
    }

    public List<Integer> getNbrInvestorRange() {
        if(nbrInvestorRange.isEmpty()) {
            nbrInvestorRange.add(50);
        }
        return nbrInvestorRange;
    }

    public int getNbrInvestorStep() {
        if(nbrInvestorStep<=0){
            return 1;
        }
        return nbrInvestorStep;
    }

    public List<Integer> getListOfEquipment() {
        return listOfEquipment;
    }

    public List<Integer> getListOfTransport() {
        return listOfTransport;
    }

    public String getFileHousehold() {
        return fileHousehold;
    }

    public String getFileInvestor() {
        return fileInvestor;
    }

    public String getFilePromoter() {
        return filePromoter;
    }

    @Override
    public String toString() {
        return "BatchSimulationMessage{" +
                "numRange=" + numRange +
                ", numStep=" + numStep +
                ", storageType=" + storageType +
                ", nbrHouseholdRange=" + nbrHouseholdRange +
                ", nbrHouseholdStep=" + nbrHouseholdStep +
                ", nbrPromoterRange=" + nbrPromoterRange +
                ", nbrPromoterStep=" + nbrPromoterStep +
                ", nbrInvestorRange=" + nbrInvestorRange +
                ", nbrInvestorStep=" + nbrInvestorStep +
                ", listOfEquipment=" + listOfEquipment +
                ", listOfTransport=" + listOfTransport +
                ", fileHousehold='" + fileHousehold + '\'' +
                ", fileInvestor='" + fileInvestor + '\'' +
                ", filePromoter='" + filePromoter + '\'' +
                '}';
    }
}
