package kobdig.sql.repository;

import kobdig.sql.tables.HouseholdState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdStateRepository extends CrudRepository<HouseholdState, Integer>{
}
