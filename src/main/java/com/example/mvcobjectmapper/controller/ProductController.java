package com.example.mvcobjectmapper.controller;

import com.example.mvcobjectmapper.entity.Product;
import com.example.mvcobjectmapper.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        String productsJson = productService.getAllProductsAsJson();
        return ResponseEntity.ok(productsJson);
    }


    @GetMapping("/{productId}")
    public Product getByIdProduct(@PathVariable Long productId) {
        return productService.getByIdProduct(productId);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody String productJson) throws JsonProcessingException {
        Product product = productService.createProductFromJson(productJson);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

