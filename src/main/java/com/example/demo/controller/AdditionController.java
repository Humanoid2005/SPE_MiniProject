package com.example.demo.controller;


import com.example.demo.service.AdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/add")
public class AdditionController {
    @Autowired
    private AdditionService service;

    @GetMapping
    public ResponseEntity<Double> compute(@RequestParam double a,@RequestParam double b){
        Double answer = service.compute(a,b);

        return ResponseEntity.ok(answer);
    }
}
