package com.example.demo.rest;

import com.example.demo.product.Product;
import com.example.demo.product.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    List<Product> all() {
        return repository.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return repository.findById(id)
                .map(products -> {
                    products.setName(newProduct.getName());
                    products.setPrice(newProduct.getPrice());

                    return repository.save(products);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}