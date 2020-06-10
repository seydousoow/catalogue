package sn.finappli.catalogue.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sn.finappli.catalogue.entities.Product;
import sn.finappli.catalogue.services.ProductService;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public Page<Product> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "20") int size,
                                 @RequestParam(required = false) String dir,
                                 @RequestParam(required = false, defaultValue = "id") String... sort) {
        var direction = Sort.Direction.fromString(dir == null || !Arrays.asList("ASC", "DESC").contains(dir.toUpperCase()) ? "ASC" : dir.toUpperCase());
        var pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        return service.registerProduct(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

}
