package kobdig.sql.repository;

import kobdig.sql.tables.PropertyState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyStateRepository extends CrudRepository<PropertyState, Integer>{
}
