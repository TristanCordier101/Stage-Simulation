package kobdig.sql.repository;

import kobdig.sql.tables.LandState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LandStateRepository extends CrudRepository<LandState, Integer>{

    @Query(value = "SELECT * FROM land_state c WHERE c.idSimulation = :id", nativeQuery = true)
    List<LandState> findByidSimulation(@Param("id")int id);
}
