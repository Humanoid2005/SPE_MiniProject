package com.example.demo.controller;

import com.example.demo.service.AdditionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdditionController.class)
class AdditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdditionService additionService;

    @Test
    @DisplayName("GET /api/v1/add?a=10&b=20 should return 200 OK and 30.0")
    void testCompute_Success() throws Exception {
        when(additionService.compute(10.0, 20.0)).thenReturn(30.0);

        mockMvc.perform(get("/api/v1/add")
                        .param("a", "10.0")
                        .param("b", "20.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("30.0"));
    }

    @Test
    @DisplayName("GET /api/v1/add without required params should return 400 Bad Request")
    void testCompute_MissingParams() throws Exception {
        mockMvc.perform(get("/api/v1/add"))
                .andExpect(status().isBadRequest());
    }
}