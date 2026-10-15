package com.reon.demoapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        @NotNull(message = "Order Id is required")
        Long orderId,

        @NotNull(message = "Payment amount is required")
        @Positive(message = "Amount should be positive")
        BigDecimal amount
) {
}
