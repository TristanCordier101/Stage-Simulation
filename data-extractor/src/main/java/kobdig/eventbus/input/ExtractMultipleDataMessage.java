package kobdig.eventbus.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 11/12/2017.
 */
public class ExtractMultipleDataMessage {
    private List<String> entity;
    private List<Integer> idSimulationRange= new ArrayList<>();
    private int storageType;

    public List<String> getEntity() {
        return entity;
    }

    public List<Integer> getIdSimulationRange() {
        return idSimulationRange;
    }

    public int getStorageType() {
        return storageType;
    }

    @Override
    public String toString() {
        return "ExtractDataMessage{" +
                "entity='" + entity + '\'' +
                ", idSimulationRange=" + idSimulationRange +
                ", storageType=" + storageType +
                '}';
    }
}
