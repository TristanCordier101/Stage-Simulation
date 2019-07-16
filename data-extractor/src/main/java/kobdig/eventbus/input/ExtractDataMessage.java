package kobdig.eventbus.input;

/**
 * Created by Matthieu on 11/12/2017.
 */
public class ExtractDataMessage {
    private String entity;
    private int idSimulation;
    private int storageType;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getIdSimulation() {
        return idSimulation;
    }

    public void setIdSimulation(int idSimulation) {
        this.idSimulation = idSimulation;
    }

    public int getStorageType() {
        return storageType;
    }

    public void setStorageType(int storageType) {
        this.storageType = storageType;
    }
}
