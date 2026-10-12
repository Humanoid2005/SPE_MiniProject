package com.example.demo.models;

public record CalculationResponse(String operation, double a, double b, double result) {
    public static void validateInputs(double a, double b) {
        if (!Double.isFinite(a) || !Double.isFinite(b)) {
            throw new IllegalArgumentException("Parameters a and b must be finite numbers");
        }
    }

    public CalculationResponse {
        validateInputs(a, b);
        if (!Double.isFinite(result)) {
            throw new IllegalArgumentException("Result is outside the supported numeric range");
        }
    }
}
