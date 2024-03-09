package com.paymentapp.controller;

import com.paymentapp.entity.Product;
import com.paymentapp.repository.ProductRepository;
import com.paymentapp.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Value("{stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentService paymentService;

    //http://localhost:8080/payments/initiate?productId=1
    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestParam Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                String paymentIntentId = paymentService.createPaymentIntent(Double.parseDouble(product.getPrice()));

                return ResponseEntity.ok(paymentIntentId);

    }
}
