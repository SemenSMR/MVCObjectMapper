package com.example.mvcobjectmapper.service;

import com.example.mvcobjectmapper.entity.Order;
import com.example.mvcobjectmapper.entity.Product;
import com.example.mvcobjectmapper.exception.ProductNotFoundException;
import com.example.mvcobjectmapper.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;


    public String getAllProductsAsJson() throws JsonProcessingException {
        List<Product> products = productRepository.findAll();
        return objectMapper.writeValueAsString(products);
    }

    public Product getByIdProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product createProductFromJson(String productJson) throws JsonProcessingException {
        Product product = objectMapper.readValue(productJson, Product.class);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantityInStock(productDetails.getQuantityInStock());
        return productRepository.save(product);

    }
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }

}
