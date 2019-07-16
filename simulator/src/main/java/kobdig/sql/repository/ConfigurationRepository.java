package kobdig.sql.repository;

import kobdig.sql.tables.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {

    @Query(value = "SELECT * FROM configuration_state c WHERE c.idSimulation = :id", nativeQuery = true)
    Configuration findByidSimulation(@Param("id")int id);
}
