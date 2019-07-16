package kobdig.sql.repository;

import kobdig.sql.tables.LandState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandStateRepository extends CrudRepository<LandState, Integer>{
}
