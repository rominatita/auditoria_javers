package audit.test.demo.config;

import org.javers.spring.auditable.CommitPropertiesProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaversConfiguration {
    @Bean
    public CommitPropertiesProvider commitPropertiesProvider() {
        return new CommitPropertiesProvider() {
            @Override
            public Map<String, String> provideForCommittedObject(Object domainObject) {
                Map<String, String> properties = new HashMap<>();
                //Agregar manejo cuando no tiene los headers
                properties.put("Ip-Address", UserContextHolder.getContext().getIpAddress());
                properties.put("X-User-Id", UserContextHolder.getContext().getUserName());
                return properties;
            }
        };
    }

}
