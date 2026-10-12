package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionServiceTest {

    private DivisionService divisionService;

    @BeforeEach
    void setUp() {
        divisionService = new DivisionService();
    }

    @Test
    @DisplayName("Should return null when dividing by zero")
    void testCompute_DivideByZero() {
        Double result = divisionService.compute(10.0, 0.0);
        assertNull(result, "Dividing by zero should return null");
    }

    @Test
    @DisplayName("Should divide two non-zero numbers correctly")
    void testCompute_ValidDivision() {
        Double result = divisionService.compute(10.0, 2.0);
        assertNotNull(result);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should divide zero by a non-zero number correctly")
    void testCompute_ZeroNumerator() {
        Double result = divisionService.compute(0.0, 5.0);
        assertNotNull(result);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    @DisplayName("Should handle division with negative numbers correctly")
    void testCompute_NegativeNumbers() {
        Double result = divisionService.compute(-10.0, 2.0);
        assertEquals(-5.0, result, 0.0001);

        Double result2 = divisionService.compute(-10.0, -2.0);
        assertEquals(5.0, result2, 0.0001);
    }
}