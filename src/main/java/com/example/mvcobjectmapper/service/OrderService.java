package com.example.mvcobjectmapper.service;

import com.example.mvcobjectmapper.entity.Order;
import com.example.mvcobjectmapper.entity.Product;
import com.example.mvcobjectmapper.exception.OrderNotFoundException;
import com.example.mvcobjectmapper.repository.CustomerRepository;
import com.example.mvcobjectmapper.repository.OrderRepository;
import com.example.mvcobjectmapper.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ObjectMapper objectMapper;


    public Order createOrderFromJson(String orderJson) throws JsonProcessingException {
        Order order = objectMapper.readValue(orderJson, Order.class);
        return orderRepository.save(order);
    }

    public String getOrderAsJson(Long orderId) throws JsonProcessingException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return objectMapper.writeValueAsString(order);
    }
}
