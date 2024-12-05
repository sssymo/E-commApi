package org.example.repository;
import java.util.*;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Utente> findByCustomer(Customer c);

        List<Order> findByCustomerId(Long customerId); // Find orders by customer ID

}
