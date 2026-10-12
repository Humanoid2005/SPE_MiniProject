package com.example.demo.controller;

import com.example.demo.models.CalculationResponse;
import com.example.demo.service.AdditionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/add")
public class AdditionController {
    private final AdditionService service;

    public AdditionController(AdditionService service) {
        this.service = service;
    }

    @GetMapping
    public CalculationResponse compute(@RequestParam double a, @RequestParam double b) {
        CalculationResponse.validateInputs(a, b);
        Double answer = service.compute(a, b);
        return new CalculationResponse("add", a, b, answer);
    }
}
