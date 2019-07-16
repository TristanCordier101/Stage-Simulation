package kobdig.controller;

import kobdig.eventbus.input.ExtractDataMessage;
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
    public ResponseEntity<Void> extractData(@RequestBody ExtractDataMessage message){
        switch(message.getEntity()){
            case "household":
                if(message.getStorageType()==0){
                    extractor.findHouseholdsBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findHouseholdsBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            case "promoter":
                if(message.getStorageType()==0){
                    extractor.findPromotersBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findPromotersBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            case "investor":
                if(message.getStorageType()==0){
                    extractor.findInvestorsBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findInvestorsBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            case "land":
                if(message.getStorageType()==0){
                    extractor.findLandsBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findLandsBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            case "property":
                if(message.getStorageType()==0){
                    extractor.findPropertiesBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findPropertiesBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            case "configuration":
                if(message.getStorageType()==0){
                    extractor.findConfigurationBySimulationIdMongo(message.getIdSimulation());
                }else{
                    extractor.findConfigurationBySimulationIdPostgre(message.getIdSimulation());
                }
                break;
            default:
                break;
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}