package audit.test.demo;

import audit.test.demo.domain.Address;
import audit.test.demo.domain.Product;
import audit.test.demo.domain.Store;
import audit.test.demo.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditApplication.class, args);
	}
	@Autowired
	StoreRepository storeRepository;


	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		// Insertando datos de prueba
		Store store = new Store("Baeldung store", new Address("Some street", 22222));
		for (int i = 1; i < 3; i++) {
			Product product = new Product("Product #" + i, 100 * i);
			store.addProduct(product);
		}
		storeRepository.save(store);
	}
}
