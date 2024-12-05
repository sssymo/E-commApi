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

            Utente user1 = new Utente();
            user1.setUsername("john_doe");
            user1.setPassword(passwordEncoder().encode("password123"));
            user1.setRoles(Set.of("USER"));

            Utente user2 = new Utente();
            user2.setUsername("jane_smith");
            user2.setPassword(passwordEncoder().encode("password456"));
            user2.setRoles(Set.of("USER", "ADMIN"));

            userRepository.saveAll(List.of(user1, user2));

            Customer customer1 = new Customer();
            customer1.setName("John Doe");
            customer1.setEmail("john.doe@example.com");

            Customer customer2 = new Customer();
            customer2.setName("Jane Smith");
            customer2.setEmail("jane.smith@example.com");

            customerRepository.saveAll(List.of(customer1, customer2));

            Seller seller1 = new Seller();
            seller1.setUsername("seller1");
            seller1.setPassword(passwordEncoder().encode("sellerpassword1"));
            seller1.setStoreName("John's Tech Store");

            Seller seller2 = new Seller();
            seller2.setUsername("seller2");
            seller2.setPassword(passwordEncoder().encode("sellerpassword2"));
            seller2.setStoreName("Jane's Gadget Shop");

            Seller seller3 = new Seller();
            seller3.setUsername("seller3");
            seller3.setPassword(passwordEncoder().encode("sellerpassword3"));
            seller3.setStoreName("Tech World");

            sellerRepository.saveAll(List.of(seller1, seller2, seller3));

            Product product1 = new Product();
            product1.setName("Laptop");
            product1.setDescription("High-performance laptop");
            product1.setPrice(899.99);
            product1.setSeller(seller1);

            Product product2 = new Product();
            product2.setName("Wireless Mouse");
            product2.setDescription("Ergonomic wireless mouse");
            product2.setPrice(19.99);
            product2.setSeller(seller2);

            Product product3 = new Product();
            product3.setName("Keyboard");
            product3.setDescription("Mechanical keyboard");
            product3.setPrice(49.99);
            product3.setSeller(seller1);

            Product product4 = new Product();
            product4.setName("Smartphone");
            product4.setDescription("Latest Android smartphone");
            product4.setPrice(599.99);
            product4.setSeller(seller3);

            Product product5 = new Product();
            product5.setName("Headphones");
            product5.setDescription("Noise-cancelling headphones");
            product5.setPrice(129.99);
            product5.setSeller(seller2);

            Product product6 = new Product();
            product6.setName("Tablet");
            product6.setDescription("10-inch tablet with stylus");
            product6.setPrice(249.99);
            product6.setSeller(seller3);

            Product product7 = new Product();
            product7.setName("Smartwatch");
            product7.setDescription("Fitness tracking smartwatch");
            product7.setPrice(199.99);
            product7.setSeller(seller1);

            Product product8 = new Product();
            product8.setName("USB-C Hub");
            product8.setDescription("Multi-port USB-C hub");
            product8.setPrice(29.99);
            product8.setSeller(seller2);

            Product product9 = new Product();
            product9.setName("Bluetooth Speaker");
            product9.setDescription("Portable Bluetooth speaker");
            product9.setPrice(79.99);
            product9.setSeller(seller3);

            Product product10 = new Product();
            product10.setName("Wireless Charger");
            product10.setDescription("Fast wireless charger");
            product10.setPrice(39.99);
            product10.setSeller(seller1);

            Product product11 = new Product();
            product11.setName("Game Console");
            product11.setDescription("Next-gen gaming console");
            product11.setPrice(499.99);
            product11.setSeller(seller2);

            Product product12 = new Product();
            product12.setName("Action Camera");
            product12.setDescription("4K action camera");
            product12.setPrice(149.99);
            product12.setSeller(seller3);

            productRepository.saveAll(List.of(
                    product1, product2, product3, product4, product5, product6,
                    product7, product8, product9, product10, product11, product12
            ));

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

            orderItemRepository.saveAll(List.of(orderItem1, orderItem2, orderItem3));   };
    }
}
