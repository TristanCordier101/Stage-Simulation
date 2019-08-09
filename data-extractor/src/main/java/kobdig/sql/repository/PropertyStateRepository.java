package kobdig.sql.repository;

import kobdig.sql.tables.PropertyState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyStateRepository extends CrudRepository<PropertyState, Integer>{

    @Query(value = "SELECT * FROM ((SELECT gid, geom from land) s INNER JOIN property_state p ON p.idland = s.gid) j WHERE j.idSimulation = :id", nativeQuery = true)
    List<PropertyState> findByidSimulation(@Param("id")int id);

}
