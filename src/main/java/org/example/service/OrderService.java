package org.example.service;
import org.example.model.*;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;


    public Order createOrder(Order order) {
 return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    public Order updateOrder(Long id, Order updatedOrder) {

        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            existingOrder.setStatus(updatedOrder.isStatus());
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());

            if (updatedOrder.getCustomer() != null) {
                existingOrder.setCustomer(updatedOrder.getCustomer());
            }

           if (updatedOrder.getItems() != null && !updatedOrder.getItems().isEmpty()) {
                existingOrder.setItems(updatedOrder.getItems());
                for (OrderItem item : updatedOrder.getItems()) {
                    item.setOrder(existingOrder);
                }
            }

            return orderRepository.save(existingOrder);
        } else {

            throw new RuntimeException("Order not found  " + id);
        }
    }

}
