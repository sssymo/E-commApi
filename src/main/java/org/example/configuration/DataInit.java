package org.example.configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.model.*;
import org.example.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInit {

    @Bean
    public CommandLineRunner loadData(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {
        return args -> {

            Customer customer1 = new Customer();
            customer1.setName("John Doe");
            customer1.setEmail("john.doe@example.com");

            Customer customer2 = new Customer();
            customer2.setName("Jane Smith");
            customer2.setEmail("jane.smith@example.com");

            customerRepository.saveAll(List.of(customer1, customer2));

            Product product1 = new Product();
            product1.setName("Laptop");
            product1.setDescription("High-performance laptop");
            product1.setPrice(899.99);

            Product product2 = new Product();
            product2.setName("Mouse");
            product2.setDescription("Wireless mouse");
            product2.setPrice(19.99);

            productRepository.saveAll(List.of(product1, product2));

            Order order1 = new Order();
            order1.setOrderDate(LocalDateTime.now());
            order1.setStatus(false);
            order1.setTotalAmount(919.98);
            order1.setCustomer(customer1);

            Order order2 = new Order();
            order2.setOrderDate(LocalDateTime.now());
            order2.setStatus(true);
            order2.setTotalAmount(899.99);
            order2.setCustomer(customer2);

            orderRepository.saveAll(List.of(order1, order2));

            OrderItem orderItem1 = new OrderItem();

            orderItem1.setOrder(order1);
            orderItem1.setProduct(product1);
            orderItem1.setQuantity(1);
            orderItem1.setPrice(899.99);

            OrderItem orderItem2 = new OrderItem();

            orderItem2.setOrder(order1);
            orderItem2.setProduct(product2);
            orderItem2.setQuantity(1);
            orderItem2.setPrice(19.99);

            OrderItem orderItem3 = new OrderItem();

            orderItem3.setOrder(order2);
            orderItem3.setProduct(product1);
            orderItem3.setQuantity(1);
            orderItem3.setPrice(899.99);

            orderItemRepository.saveAll(List.of(orderItem1, orderItem2, orderItem3));
        };
    }
}
