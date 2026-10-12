package com.example.demo.controller;

import com.example.demo.models.CalculationResponse;
import com.example.demo.service.MultiplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/multiply")
public class MultiplicationController {
    private final MultiplicationService service;

    public MultiplicationController(MultiplicationService service) {
        this.service = service;
    }

    @GetMapping
    public CalculationResponse compute(@RequestParam double a, @RequestParam double b) {
        CalculationResponse.validateInputs(a, b);
        Double answer = service.compute(a, b);
        return new CalculationResponse("multiply", a, b, answer);
    }
}
