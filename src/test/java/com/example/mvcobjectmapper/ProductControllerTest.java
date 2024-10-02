package com.example.mvcobjectmapper;

import com.example.mvcobjectmapper.entity.Product;
import com.example.mvcobjectmapper.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(100));
        product.setQuantityInStock(50);

    }

    @Test
    void testGetAllProducts() throws Exception {
        String productsJson = objectMapper.writeValueAsString(List.of(product));

        Mockito.when(productService.getAllProductsAsJson()).thenReturn(productsJson);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(productsJson));
    }

    @Test
    void testGetByIdProduct() throws Exception {
        Mockito.when(productService.getByIdProduct(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testCreateProduct() throws Exception {
        String productJson = objectMapper.writeValueAsString(product);

        Mockito.when(productService.createProductFromJson(Mockito.anyString())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        String updatedProductJson = objectMapper.writeValueAsString(product);

        Mockito.when(productService.updateProduct(Mockito.eq(1L), Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/1"))
                .andExpect(status().isOk());

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }

}
