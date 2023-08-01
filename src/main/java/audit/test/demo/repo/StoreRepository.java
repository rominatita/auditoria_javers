package audit.test.demo.repo;

import audit.test.demo.domain.Store;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface StoreRepository extends CrudRepository<Store, Integer> {
}
