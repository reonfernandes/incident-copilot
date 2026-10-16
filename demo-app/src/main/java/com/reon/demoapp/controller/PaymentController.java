package com.reon.demoapp.controller;

import com.reon.demoapp.dto.PaymentRequest;
import com.reon.demoapp.dto.PaymentResponse;
import com.reon.demoapp.exception.PaymentGatewayTimeoutException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {

    private static final int FAILURE_RATE_PERCENT = 30;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@Valid @RequestBody PaymentRequest request) {
        log.info("processing payment for order: {}", request.orderId());

        if (ThreadLocalRandom.current().nextInt(100) < FAILURE_RATE_PERCENT) {
            throw new PaymentGatewayTimeoutException("Payment gateway did not respond within 5000 ms");
        }

        return ResponseEntity.ok(PaymentResponse.builder()
                .orderId(request.orderId())
                .amount(request.amount())
                .status("PAID")
                .build());
    }
}
