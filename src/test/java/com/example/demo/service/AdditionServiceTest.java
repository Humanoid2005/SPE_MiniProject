package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionServiceTest {

    private AdditionService additionService;

    @BeforeEach
    void setUp() {
        additionService = new AdditionService();
    }

    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testCompute_PositiveNumbers() {
        Double result = additionService.compute(5.0, 3.0);
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should add negative and positive numbers correctly")
    void testCompute_NegativeNumbers() {
        Double result = additionService.compute(-5.0, 3.0);
        assertEquals(-2.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should add two negative numbers correctly")
    void testCompute_BothNegativeNumbers() {
        Double result = additionService.compute(-5.0, -3.0);
        assertEquals(-8.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should handle zero correctly")
    void testCompute_WithZero() {
        Double result = additionService.compute(5.0, 0.0);
        assertEquals(5.0, result, 0.0001);
    }
}