package audit.test.demo.config;

import audit.test.demo.domain.Auditable;
import audit.test.demo.domain.Product;
import org.javers.common.collections.Maps;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.javers.spring.auditable.SpringSecurityAuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaversConfiguration {
    @Bean
    public CommitPropertiesProvider commitPropertiesProvider() {
        return new CommitPropertiesProvider() {
            @Override
            public Map<String, String> provideForCommittedObject(Object domainObject) {
                if (domainObject instanceof Auditable) {
                    Map<String, String> properties = new HashMap<>();
                    properties.put("ip", ((Auditable)domainObject).getIp());
                    properties.put("author", ((Auditable)domainObject).getAuthor());
                    return properties;
                }
                return Collections.emptyMap();
            }
        };
    }

}
