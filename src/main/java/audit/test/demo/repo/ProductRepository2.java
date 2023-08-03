package audit.test.demo.repo;

import audit.test.demo.domain.Product;
import org.javers.spring.annotation.JaversAuditable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository2 {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @JaversAuditable
    public void save(Product product) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", 1);
        parameters.addValue("price", product.getPrice());

        String update = "update Product set price = :price where id = :id";

        jdbcTemplate.update(update, parameters);
    }
}
