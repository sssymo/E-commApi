package org.example.configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.model.*;
import org.example.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInit {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CommandLineRunner loadData(
            SellerRepository sellerRepository,
            UserRepository userRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {
        return args -> {

            // Creazione di Customer (utenti)
            Customer customer1 = new Customer();
            customer1.setUsername("john_doe");
            customer1.setPassword(passwordEncoder().encode("password123"));
            customer1.setRoles(Set.of("USER"));
            customer1.setEmail("john.doe@example.com");

            Customer customer2 = new Customer();
            customer2.setUsername("jane_smith");
            customer2.setPassword(passwordEncoder().encode("password456"));
            customer2.setRoles(Set.of("USER"));
            customer2.setEmail("jane.smith@example.com");

            	Utente admin = new Utente();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder().encode("adminpassword"));
            admin.setRoles(Set.of("ADMIN"));
            
userRepository.save(admin);
            customerRepository.saveAll(List.of(customer1, customer2));

            // Creazione di Seller (utenti)
            Seller seller1 = new Seller();
            seller1.setUsername("seller1");
            seller1.setPassword(passwordEncoder().encode("sellerpassword1"));
            seller1.setRoles(Set.of("SELLER"));
            seller1.setStoreName("John's Tech Store");

            Seller seller2 = new Seller();
            seller2.setUsername("seller2");
            seller2.setPassword(passwordEncoder().encode("sellerpassword2"));
            seller2.setRoles(Set.of("SELLER"));
            seller2.setStoreName("Jane's Gadget Shop");

            sellerRepository.saveAll(List.of(seller1, seller2));

            // Creazione di Product
            List<Product> products = List.of(
                new Product("Laptop", "High-performance laptop", 899.99, seller1),
                new Product("Wireless Mouse", "Ergonomic wireless mouse", 19.99, seller2)
            );

            productRepository.saveAll(products);

            // Creazione di Order
            List<Order> orders = List.of(
                new Order(null, LocalDateTime.now(), false, 919.98, customer1, null),
                new Order(null, LocalDateTime.now().minusDays(2), true, 599.99, customer2, null)
            );

            orderRepository.saveAll(orders);

            // Creazione di OrderItem
            List<OrderItem> orderItems = List.of(
                new OrderItem(orders.get(0), products.get(0), 1, 899.99),
                new OrderItem(orders.get(0), products.get(1), 1, 19.99),
                new OrderItem(orders.get(1), products.get(0), 2, 1799.98)
            );

            orderItemRepository.saveAll(orderItems);
 
};
    }
}
