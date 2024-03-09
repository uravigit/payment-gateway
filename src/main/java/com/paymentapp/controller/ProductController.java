package com.paymentapp.controller;

import com.paymentapp.entity.Product;
import com.paymentapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class ProductController {

    @Autowired
    private ProductRepository paymentRepository;

    @GetMapping ("/process")
    public List<Product> getAllPayment() {

        return paymentRepository.findAll();
    }
}

