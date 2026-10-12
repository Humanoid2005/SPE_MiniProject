package com.example.demo.controller;

import com.example.demo.service.SubtractionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubtractionController.class)
class SubtractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubtractionService subtractionService;

    @Test
    @DisplayName("GET /api/v1/subtract?a=10&b=20 should return 200 OK and -10.0")
    void testCompute_Success() throws Exception {
        when(subtractionService.compute(10.0, 20.0)).thenReturn(-10.0);

        mockMvc.perform(get("/api/v1/subtract")
                        .param("a", "10.0")
                        .param("b", "20.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("-10.0"));
    }
}