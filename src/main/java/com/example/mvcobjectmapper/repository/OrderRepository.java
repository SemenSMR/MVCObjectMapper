package com.example.mvcobjectmapper.repository;


import com.example.mvcobjectmapper.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
