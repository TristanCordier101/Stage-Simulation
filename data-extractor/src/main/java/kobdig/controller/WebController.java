package kobdig.controller;

import kobdig.eventbus.input.ExtractDataMessage;
import kobdig.eventbus.input.ExtractMultipleDataMessage;
import kobdig.service.DataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    public DataExtractor extractor;


    @PostMapping("/extract")
    public ResponseEntity<Void> extractData(@RequestBody ExtractMultipleDataMessage message) {
        if (!message.getIdSimulationRange().isEmpty()){
            for (String entity : message.getEntity()) {
                switch (entity) {
                    case "household":
                        if (message.getStorageType() == 0) {
                            extractor.findHouseholdsBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findHouseholdsBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    case "promoter":
                        if (message.getStorageType() == 0) {
                            extractor.findPromotersBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findPromotersBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    case "investor":
                        if (message.getStorageType() == 0) {
                            extractor.findInvestorsBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findInvestorsBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    case "land":
                        if (message.getStorageType() == 0) {
                            extractor.findLandsBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findLandsBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    case "property":
                        if (message.getStorageType() == 0) {
                            extractor.findPropertiesBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findPropertiesBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    case "configuration":
                        if (message.getStorageType() == 0) {
                            extractor.findConfigurationBySimulationIdMongo(message.getIdSimulationRange());
                        } else {
                            extractor.findConfigurationBySimulationIdPostgre(message.getIdSimulationRange());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}