package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionServiceTest {

    private SubtractionService subtractionService;

    @BeforeEach
    void setUp() {
        subtractionService = new SubtractionService();
    }

    @Test
    @DisplayName("Should subtract two numbers correctly")
    void testCompute_ValidSubtraction() {
        Double result = subtractionService.compute(10.0, 3.0);
        assertEquals(7.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should subtract negative numbers correctly")
    void testCompute_NegativeNumbers() {
        Double result = subtractionService.compute(-5.0, -3.0);
        assertEquals(-2.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should result in negative when subtracting larger from smaller")
    void testCompute_NegativeResult() {
        Double result = subtractionService.compute(3.0, 10.0);
        assertEquals(-7.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should handle zero correctly")
    void testCompute_WithZero() {
        Double result = subtractionService.compute(5.0, 0.0);
        assertEquals(5.0, result, 0.0001);
    }
}