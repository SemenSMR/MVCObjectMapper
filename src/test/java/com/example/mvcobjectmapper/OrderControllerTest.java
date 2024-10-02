package com.example.mvcobjectmapper;

import com.example.mvcobjectmapper.entity.Order;
import com.example.mvcobjectmapper.service.OrderService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId(1L);
        order.setTotalPrice(BigDecimal.valueOf(500));
        order.setOrderStatus("Processing");

    }

    @Test
    void testCreateOrder() throws Exception {
        String orderJson = objectMapper.writeValueAsString(order);

        Mockito.when(orderService.createOrderFromJson(Mockito.anyString())).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.totalPrice").value(500));
    }

    @Test
    void testGetOrder() throws Exception {
        String orderJson = objectMapper.writeValueAsString(order);

        Mockito.when(orderService.getOrderAsJson(1L)).thenReturn(orderJson);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(orderJson));
    }
}
