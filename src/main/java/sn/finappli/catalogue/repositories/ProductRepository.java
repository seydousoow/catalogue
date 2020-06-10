package sn.finappli.catalogue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.finappli.catalogue.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
