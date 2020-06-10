package sn.finappli.catalogue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.finappli.catalogue.entities.Product;
import sn.finappli.catalogue.services.ProductService;

import java.util.Arrays;

@SpringBootApplication
public class CatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService service) {
        return args -> Arrays.asList(
                new Product(null, "Lenovo t430", 490000.0),
                new Product(null, "Lenovo t440", 540000.0),
                new Product(null, "Lenovo s500", 570000.0),
                new Product(null, "Lenovo s550", 600000.0),
                new Product(null, "Lenovo x1 carbon", 820000.0),
                new Product(null, "Iphone X", 400000.0),
                new Product(null, "Iphone Xs", 440000.0),
                new Product(null, "Iphone Xs Max", 500000.0),
                new Product(null, "Iphone XR", 350000.0),
                new Product(null, "Iphone 11", 550000.0),
                new Product(null, "Iphone 11 Pro", 690000.0),
                new Product(null, "Iphone 11 Pro Max", 850000.0)
        ).forEach(service::registerProduct);
    }

}
