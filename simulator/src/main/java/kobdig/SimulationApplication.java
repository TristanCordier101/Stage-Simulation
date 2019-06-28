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

    /*@Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}

/*
mvn clean install
mvn spring-boot:run
D:\programme2\programme\Kafka\kafka_2.12-2.2.0
bin\windows\zookeeper-server-start.sh config\zookeeper.properties
zkserver
bin\windows\kafka-server-start.sh config\server.properties
pg_ctl -D "D:\programme2\programme\PostgreSQL\11\data" start
https://stackoverflow.com/questions/854264/how-to-add-directory-to-classpath-in-an-application-run-profile-in-intellij-idea
run mongo.exe
curl -H "Content-Type: application/json" -X POST localhost:8080/state -d "{\"type\": \"StateSimulatorMessage\", \"value\": {\"nbrHousehold\": 50, \"nbrInvestor\": 50, \"nbrPromoter\": 50, \"num\":2, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}}"
curl -H "Content-Type: application/json" -X POST localhost:8080/state -d "{\"nbrHousehold\": 50, \"nbrInvestor\": 50, \"nbrPromoter\": 50, \"num\":10, \"listOfEquipment\":[85,81], \"listOfTransport\":[176,794], \"fileHousehold\" : \"householdAgent.apl\", \"fileInvestor\" : \"investorAgent.apl\", \"filePromoter\" : \"promoterAgent.apl\"}"
curl -H "Content-Type: application/json" -X POST localhost:8081/extract -d "{\"entity\":\"household\", \"idSimulation\":\"0\"}"
 */
