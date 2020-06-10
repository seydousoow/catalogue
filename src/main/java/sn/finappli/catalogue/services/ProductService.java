package sn.finappli.catalogue.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.finappli.catalogue.entities.Product;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Product registerProduct(Product p);

    void deleteProduct(Long id);
}
