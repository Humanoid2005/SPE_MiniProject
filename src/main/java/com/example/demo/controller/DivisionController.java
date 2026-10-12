package com.example.demo.controller;

import com.example.demo.models.CalculationResponse;
import com.example.demo.service.DivisionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/divide")
public class DivisionController {
    private final DivisionService service;

    public DivisionController(DivisionService service) {
        this.service = service;
    }

    @GetMapping
    public CalculationResponse compute(@RequestParam double a, @RequestParam double b) {
        CalculationResponse.validateInputs(a, b);
        Double answer = service.compute(a, b);
        if (answer == null) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        return new CalculationResponse("divide", a, b, answer);
    }
}
