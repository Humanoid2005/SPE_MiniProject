package com.example.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({"add,5,3,8", "subtract,5,3,2", "multiply,5,3,15", "divide,5,2,2.5"})
    void actualServicesReturnStructuredResults(String operation, double a, double b, double result) throws Exception {
        mockMvc.perform(get("/api/v1/" + operation).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.operation").value(operation))
                .andExpect(jsonPath("$.a").value(a))
                .andExpect(jsonPath("$.b").value(b))
                .andExpect(jsonPath("$.result").value(result));
    }

    @ParameterizedTest
    @CsvSource({"add", "subtract", "multiply", "divide"})
    void missingInputsReturnStructuredErrors(String operation) throws Exception {
        mockMvc.perform(get("/api/v1/" + operation).param("a", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Missing required parameter: b"));
    }

    @ParameterizedTest
    @CsvSource({"add,text,2", "subtract,2,text", "multiply,NaN,2", "divide,2,Infinity",
            "add,1e308,1e308", "multiply,1e308,1e308", "divide,2,0", "divide,2,-0.0"})
    void invalidInputsReturnStructuredErrors(String operation, String a, String b) throws Exception {
        mockMvc.perform(get("/api/v1/" + operation).param("a", a).param("b", b))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
