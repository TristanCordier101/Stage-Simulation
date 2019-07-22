package kobdig.sql.repository;

import kobdig.sql.tables.PropertyState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyStateRepository extends CrudRepository<PropertyState, Integer>{

    @Query(value = "SELECT * FROM (SELECT * FROM property_state p WHERE p.idSimulation = :id) q INNER JOIN (SELECT gid, geom from land) s ON q.idland = s.gid ", nativeQuery = true)
    List<PropertyState> findByidSimulation(@Param("id")int id);

}
