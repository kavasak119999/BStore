package edu.max.bstore.service;

import edu.max.bstore.dto.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    void createOrder(Order order);
    void switchStatusToOrder(String id);
}
