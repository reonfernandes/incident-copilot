package com.reon.demoapp.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentResponse(
        Long orderId,
        BigDecimal amount,
        String status
) {
}
