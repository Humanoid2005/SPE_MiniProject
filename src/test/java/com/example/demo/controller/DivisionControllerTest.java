package com.example.demo.controller;

import com.example.demo.service.DivisionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DivisionController.class)
class DivisionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivisionService divisionService;

    @Test
    @DisplayName("GET /api/v1/divide?a=10&b=2 should return 200 OK and result")
    void testCompute_Success() throws Exception {
        when(divisionService.compute(10.0, 2.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/v1/divide")
                        .param("a", "10.0")
                        .param("b", "2.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    @DisplayName("GET /api/v1/divide?a=10&b=0 should return 400 Bad Request with error message")
    void testCompute_DivideByZero() throws Exception {
        when(divisionService.compute(10.0, 0.0)).thenReturn(null);

        mockMvc.perform(get("/api/v1/divide")
                        .param("a", "10.0")
                        .param("b", "0.0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Denominator cannot be 0"));
    }
}