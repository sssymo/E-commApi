package org.example.controller;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

            if (product.getName() == null || product.getName().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            productService.deleteProduct(optionalProduct.get());
            return ResponseEntity.noContent().build(); //204
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {

        Optional<Product> existingProduct = productService.getProductById(id);

        if (existingProduct.isPresent()) {
            Product existing = existingProduct.get();

            if (product.getName() != null) {
                existing.setName(product.getName());
            }
            if (product.getDescription() != null) {
                existing.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                existing.setPrice(product.getPrice());
            }
            productService.createProduct(existing);
            return ResponseEntity.ok("proddot aggiornato");
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodottonon trovato: " + id);
        }
    }

}
