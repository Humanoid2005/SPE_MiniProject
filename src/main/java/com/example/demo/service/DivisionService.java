package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DivisionService {
    public Double compute(double a,double b){
        if(b==0){
            return null;
        }

        return a / b;
    }
}