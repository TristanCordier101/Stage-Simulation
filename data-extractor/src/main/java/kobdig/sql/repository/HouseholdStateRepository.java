package kobdig.sql.repository;

import kobdig.sql.tables.HouseholdState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HouseholdStateRepository extends CrudRepository<HouseholdState, Integer>{

    @Query(value = "SELECT * FROM household_state c WHERE c.idSimulation = :id", nativeQuery = true)
    List<HouseholdState> findByidSimulation(@Param("id")int id);
}
