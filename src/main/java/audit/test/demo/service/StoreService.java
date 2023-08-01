package audit.test.demo.service;


import audit.test.demo.domain.Product;
import audit.test.demo.domain.Store;
import audit.test.demo.repo.ProductRepository;
import audit.test.demo.repo.StoreRepository;
import org.javers.core.Javers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class StoreService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final StoreRepository storeRepository;
    private final Javers javers;


    public StoreService(ProductRepository productRepository, StoreRepository storeRepository, Javers javers) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.javers = javers;
    }

    public void updateProductPrice(Integer productId, Double price, String changeAuthor, String ip) {
        Optional<Product> productOpt = productRepository.findById(productId);

        productOpt.ifPresent(product -> {
            product.setAuthor(changeAuthor);
            product.setIp(ip);
            product.setPrice(price);
            productRepository.save(product);
        });
    }

    public void rebrandStore(int storeId, String updatedName) {
        Optional<Store> storeOpt = storeRepository.findById(storeId);
        storeOpt.ifPresent(store -> {
            store.setName(updatedName);
            store.getProducts().forEach(product -> {
                product.setNamePrefix(updatedName);
            });
            storeRepository.save(store);
        });
    }

    public void createRandomProduct(Integer storeId) {
        Optional<Store> storeOpt = this.storeRepository.findById(storeId);
        storeOpt.ifPresent(store -> {
            Random random = new Random();
            Product product = new Product("Product#" + random.nextInt(), random.nextDouble() * 100);
            store.addProduct(product);
            storeRepository.save(store);
        });
    }

    public Store findStoreById(int storeId) {
        return storeRepository.findById(storeId).get();
    }

    public Product findProductById(int id) {
        return this.productRepository.findById(id).get();
    }
}
