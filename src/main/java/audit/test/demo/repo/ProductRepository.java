package audit.test.demo.repo;

import audit.test.demo.domain.Product;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
