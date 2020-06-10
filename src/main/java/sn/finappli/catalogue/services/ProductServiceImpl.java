package sn.finappli.catalogue.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sn.finappli.catalogue.entities.Product;
import sn.finappli.catalogue.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cet Id ne correspond a aucun produit!"));
    }

    @Override
    public Product registerProduct(Product p) {
        return repository.save(p);
    }

    @Override
    public void deleteProduct(Long id) {
        var item = findById(id);
        repository.delete(item);
    }
}
