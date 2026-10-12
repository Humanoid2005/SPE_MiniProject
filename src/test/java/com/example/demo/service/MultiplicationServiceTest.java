package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationServiceTest {

    private MultiplicationService multiplicationService;

    @BeforeEach
    void setUp() {
        multiplicationService = new MultiplicationService();
    }

    @Test
    @DisplayName("Should multiply two numbers correctly")
    void testCompute_ValidMultiplication() {
        Double result = multiplicationService.compute(5.0, 3.0);
        assertEquals(15.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should multiply by zero correctly")
    void testCompute_MultiplyByZero() {
        Double result = multiplicationService.compute(5.0, 0.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should multiply negative numbers correctly")
    void testCompute_NegativeNumbers() {
        Double result = multiplicationService.compute(-5.0, 3.0);
        assertEquals(-15.0, result, 0.0001);

        Double result2 = multiplicationService.compute(-5.0, -3.0);
        assertEquals(15.0, result2, 0.0001);
    }
}