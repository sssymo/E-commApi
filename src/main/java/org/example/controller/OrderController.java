package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        orderOptional.ifPresent(orderService::deleteOrder);
    }
}
//possibile post request {
//  "orderDate": "2024-12-04T14:46:38.042Z",
//  "status": true,
//  "totalAmount": 899.99,
//  "customer": {
//    "id": 1,
//    "name": "John Doe",
//    "email": "john.doe@example.com"
//  },
//  "items": [
//    {
//      "product": {
//        "id": 1,
//        "name": "Laptop",
//        "description": "High-performance laptop",
//        "price": 899.99
//      },
//      "quantity": 1,
//      "price": 899.99
//    }
//  ]
//} http://localhost:8080/swagger-ui/index.html#/