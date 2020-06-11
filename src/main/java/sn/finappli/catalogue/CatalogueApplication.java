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
                new Product(null, "Lenovo t430", "Un ordinateur est un système de traitement de l'information programmable tel que défini par Turing et qui fonctionne " +
                        "par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques. Sa structure physique actuelle fait que toutes les opérations reposent sur la logique binaire et sur des nombres formés à partir de chiffres binaires. ", 490000.0, 100),
                new Product(null, "Lenovo t440", "Un ordinateur est un système de traitement de l'information programmable tel que défini par Turing et qui fonctionne " +
                        "par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques. Sa structure physique actuelle fait que toutes les opérations reposent sur la logique binaire et sur des nombres formés à partir de chiffres binaires. ", 540000.0, 110),
                new Product(null, "Lenovo s500", "Un ordinateur est un système de traitement de l'information programmable tel que défini par Turing et qui fonctionne " +
                        "par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques. Sa structure physique actuelle fait que toutes les opérations reposent sur la logique binaire et sur des nombres formés à partir de chiffres binaires. ", 570000.0, 10),
                new Product(null, "Lenovo s550", "Un ordinateur est un système de traitement de l'information programmable tel que défini par Turing et qui fonctionne " +
                        "par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques. Sa structure physique actuelle fait que toutes les opérations reposent sur la logique binaire et sur des nombres formés à partir de chiffres binaires. ", 600000.0, 23),
                new Product(null, "Lenovo x1 carbon", "Un ordinateur est un système de traitement de l'information programmable tel que défini par Turing et qui " +
                        "fonctionne par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques. Sa structure physique actuelle fait que toutes les opérations reposent sur la logique binaire et sur des nombres formés à partir de chiffres binaires. ", 820000.0, 42),
                new Product(null, "Iphone X", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 400000.0, 42),
                new Product(null, "Iphone Xs", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 440000.0, 0),
                new Product(null, "Iphone Xs Max", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 500000.0, 1),
                new Product(null, "Iphone XR", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 350000.0, 0),
                new Product(null, "Iphone 11", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 550000.0, 0),
                new Product(null, "Iphone 11 Pro", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou encore " +
                        "mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 690000.0, 22),
                new Product(null, "Iphone 11 Pro Max", "Un smartphone (également appelé téléphone intelligent1, téléphone multifonction1 ou ordiphone au Québec1 ou " +
                        "encore mobile multifonction en France2) est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique " +
                        "numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable. ", 850000.0 ,10)
        ).forEach(service::registerProduct);
    }

}
