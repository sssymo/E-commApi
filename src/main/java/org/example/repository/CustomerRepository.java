package org.example.repository;
import org.example.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
Optional<Customer> findCustomerByName(String Name);

}