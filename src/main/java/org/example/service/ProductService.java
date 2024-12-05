package org.example.service;

import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public void updateProduct(Product product) {
        productRepository.save(product);
    }


}
