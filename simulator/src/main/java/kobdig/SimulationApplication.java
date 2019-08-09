package kobdig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
public class SimulationApplication {


    public static void main(String[] args){
        SpringApplication.run(SimulationApplication.class, args);
    }


}

/*
mvn clean install
mvn spring-boot:run

curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"nbrHousehold\": 100,\"storageType\": 1, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":10, \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"listOfEquipment\":[0], \"listOfTransport\":[269,311,866,864,918,958,965,1010,1008,1054,1098,1135,1197,1192,1248,1253,1307,1315,1355,1392,1517,41,36,59], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 10, \"nbrInvestor\": 10, \"nbrPromoter\": 10, \"num\":15, \"listOfEquipment\":[10], \"listOfTransport\":[1392], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"

RANDOM
curl -H "Content-Type: application/json" -X POST localhost:9090/statetab -d "{\"simulationMessageList\":{\"storageType\": 1, \"nbrHousehold\": 10, \"nbrInvestor\": 10, \"nbrPromoter\": 10, \"num\":5, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"},{\"storageType\": 1, \"nbrHousehold\": 10, \"nbrInvestor\": 10, \"nbrPromoter\": 10, \"num\":5, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}}"

Transport only
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"listOfEquipment\":[-1], \"listOfTransport\":[269,311,866,864,918,958,965,1010,1008,1054,1098,1135,1197,1192,1248,1253,1307,1315,1355,1392,1517,41,36,59,955,1017,959,309,253,255,249,309,911,908,1101,1139,1191,1250,1317,1395,1395,1444], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"listOfEquipment\":[-1], \"listOfTransport\":[176,784,794,793,798,796,822,819,856,852,849,885,894,891,937,932,938,984,990,986,1029,1028,1076,1077,1113,1114,1117,1165,1164,1218,1221,1220,1280,1281,1284,1332,1330,1373,1368,1374,1418,1416,1455,1453,1487,1533,1527,51,48,52,64,63,76,94,91,90,96,102,101,106,110,109,114,113,118,117,122], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"listOfEquipment\":[85,81,80,46,112,116,31,30,29,28,27], \"listOfTransport\":[176,784,794,793,798,796,822,819,856,852,849,885,894,891,937,932,938,984,990,986,1029,1028,1076,1077,1113,1114,1117,1165,1164,1218,1221,1220,1280,1281,1284,1332,1330,1373,1368,1374,1418,1416,1455,1453,1487,1533,1527,51,48,52,64,63,76,94,91,90,96,102,101,106,110,109,114,113,118,117,122], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/state -d "{\"storageType\": 1,\"nbrHousehold\": 100, \"nbrInvestor\": 100, \"nbrPromoter\": 100, \"num\":15, \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"


curl -H "Content-Type: application/json" -X POST localhost:9091/extract -d "{\"entity\":[\"property\",\"configuration\",\"investor\",\"land\",\"promoter\",\"household\"],\"storageType\": 1, \"idSimulationRange\":[0]}"


curl -H "Content-Type: application/json" -X POST localhost:9090/batch -d "{\"storageType\": 1, \"nbrHouseholdRange\": [50,150],\"nbrHouseholdStep\": 10, \"nbrInvestorRange\": [50,150],\"nbrInvestorStep\": 10, \"nbrPromoterRange\": [50,150],\"nbrPromoterStep\": 10, \"numRange\":[10], \"numStep\":1, \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/batch -d "{\"storageType\": 1, \"nbrHouseholdRange\": [100,200],\"nbrHouseholdStep\": 20, \"nbrInvestorRange\": [150],\"nbrInvestorStep\": 1, \"nbrPromoterRange\": [100,200],\"nbrPromoterStep\": 20, \"numRange\":[10], \"numStep\":1, \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:9090/batch -d "{\"storageType\": 1, \"nbrHouseholdRange\": [100,101],\"nbrHouseholdStep\": 1, \"nbrInvestorRange\": [100],\"nbrInvestorStep\": 1, \"nbrPromoterRange\": [100,101],\"nbrPromoterStep\": 1, \"numRange\":[10], \"numStep\":1, \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"

*/
