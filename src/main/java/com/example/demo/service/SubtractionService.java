package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SubtractionService {
    public Double compute(double a,double b){
        return a - b;
    }
}