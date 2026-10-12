package com.example.demo.controller;

import com.example.demo.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/divide")
public class DivisionController {

    @Autowired
    private DivisionService service;

    @GetMapping
    public ResponseEntity<?> compute(@RequestParam double a, @RequestParam double b) {
        Double answer = service.compute(a, b);

        if (answer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Denominator cannot be 0");
        }

        return ResponseEntity.ok(answer);
    }
}

