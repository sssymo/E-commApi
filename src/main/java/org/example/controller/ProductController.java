package org.example.controller;

import org.example.model.Order;
import org.example.model.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    List<Product> GetAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    Optional<Product> GetOrderById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public Product CreateProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    void DeleteOrder( @PathVariable Long id){
        Optional<Product> o= productService.getProductById(id);

        productService.deleteProduct(o.orElse(o.get()));



    }

}
