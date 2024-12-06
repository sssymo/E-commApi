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
                    new Product("Laptop", "High-performance laptop", 899.99, seller1, List.of("https://m.media-amazon.com/images/I/71U1dCuGXLL._AC_UL480_QL65_.jpg","https://m.media-amazon.com/images/I/71Vwv5YkXGL._AC_UL480_QL65_.jpg")),
                    new Product("Wireless Mouse", "Ergonomic wireless mouse", 19.99, seller2,List.of("https://m.media-amazon.com/images/I/61YQeAUIboL._AC_UY327_FMwebp_QL65_.jpg","https://m.media-amazon.com/images/I/71tqvuHgIlL._AC_UY327_FMwebp_QL65_.jpg")),
                    new Product("Gaming Keyboard", "RGB backlit mechanical keyboard", 59.99, seller1, List.of("https://m.media-amazon.com/images/I/71duf0rTDqL._AC_UY327_FMwebp_QL65_.jpg", "https://m.media-amazon.com/images/I/71fndG0ODKL._AC_SX466_.jpg","https://m.media-amazon.com/images/I/71EgvjEbK3L._AC_SX466_.jpg")),
                    new Product("Bluetooth Headphones", "Noise-canceling Bluetooth headphones", 129.99, seller2, List.of("https://m.media-amazon.com/images/I/411NxOSWI5L._AC_SX679_.jpg", "https://m.media-amazon.com/images/I/511jS3UQbsL._AC_SX679_.jpg")),
                    new Product("4K TV", "Ultra HD smart TV with Alexa compatibility", 499.99, seller1, List.of("https://m.media-amazon.com/images/I/71ka3DKN63L._AC_SX679_.jpg", "https://m.media-amazon.com/images/I/71JXZKHmS0L._AC_SX679_.jpg")),
                    new Product("Smartwatch", "Fitness tracker with heart rate monitor", 199.99, seller1, List.of("https://m.media-amazon.com/images/I/61OEuoqFqYL.__AC_SX300_SY300_QL70_FMwebp_.jpg", "https://m.media-amazon.com/images/I/61oHy8hzLrL._AC_SX679_.jpg","https://m.media-amazon.com/images/I/71tvATzC7NL._AC_SX679_.jpg")),
                     new Product("Tablet", "Portable tablet with large screen", 349.99, seller2, List.of("https://m.media-amazon.com/images/I/71t3j00C-yL._AC_SY450_.jpg", "https://m.media-amazon.com/images/I/61mYDVmJMGL._AC_SY450_.jpg")),
            new Product("Smartphone", "Latest model smartphone with advanced camera", 799.99, seller2, List.of("https://m.media-amazon.com/images/I/512W6nJ9iiL._AC_SX679_.jpg", "https://m.media-amazon.com/images/I/51JGkFPt0bL._AC_SX679_.jpg","https://m.media-amazon.com/images/I/51ZnbWxogbL._AC_SX679_.jpg"))
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
