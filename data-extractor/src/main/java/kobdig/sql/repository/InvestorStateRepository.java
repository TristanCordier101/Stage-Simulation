package kobdig.sql.repository;

import kobdig.sql.tables.InvestorState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InvestorStateRepository extends CrudRepository<InvestorState, Integer>{

    @Query(value = "SELECT * FROM investor_state c WHERE c.idSimulation = :id", nativeQuery = true)
    List<InvestorState> findByidSimulation(@Param("id")int id);
}

