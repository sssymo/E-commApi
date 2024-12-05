package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
      CustomerRepository customerRepository;


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("nntrovato " +id);
        }
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("nntrovato " +id));
    }

    public Customer getCustomerByName(String Name) {
        Optional<Customer> customer = customerRepository.findCustomerByName(Name);
        return customer.orElseThrow(() -> new RuntimeException("nntrovato " +Name));
    }
}
