package kobdig.sql.repository;

import kobdig.sql.tables.PromoterState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromoterStateRepository extends CrudRepository<PromoterState, Integer>{

    @Query(value = "SELECT * FROM promoter_state c WHERE c.idSimulation = :id", nativeQuery = true)
    List<PromoterState> findByidSimulation(@Param("id")int id);
}
