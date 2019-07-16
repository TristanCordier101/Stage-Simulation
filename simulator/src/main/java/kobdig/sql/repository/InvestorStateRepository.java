package kobdig.sql.repository;

import kobdig.sql.tables.InvestorState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorStateRepository extends CrudRepository<InvestorState, Integer>{
}

