package kobdig.sql.repository;

import kobdig.sql.tables.PromoterState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoterStateRepository extends CrudRepository<PromoterState, Integer>{
}
