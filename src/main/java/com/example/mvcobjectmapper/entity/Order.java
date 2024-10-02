package com.example.mvcobjectmapper.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private String orderStatus;
    @ManyToMany
    private List<Product> products;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }




}
