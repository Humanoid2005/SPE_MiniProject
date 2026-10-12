package com.example.demo.controller;

import com.example.demo.models.CalculationResponse;
import com.example.demo.service.SubtractionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subtract")
public class SubtractionController {
    private final SubtractionService service;

    public SubtractionController(SubtractionService service) {
        this.service = service;
    }

    @GetMapping
    public CalculationResponse compute(@RequestParam double a, @RequestParam double b) {
        CalculationResponse.validateInputs(a, b);
        Double answer = service.compute(a, b);
        return new CalculationResponse("subtract", a, b, answer);
    }
}
