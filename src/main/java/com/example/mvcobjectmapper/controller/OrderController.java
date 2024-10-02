package com.example.mvcobjectmapper.controller;

import com.example.mvcobjectmapper.entity.Order;
import com.example.mvcobjectmapper.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody String orderJson) throws JsonProcessingException {
        Order order = orderService.createOrderFromJson(orderJson);
        return ResponseEntity.ok(order);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<String> getOrder(@PathVariable Long orderId) throws JsonProcessingException {
        String orderJson = orderService.getOrderAsJson(orderId);
        return ResponseEntity.ok(orderJson);
    }
}
