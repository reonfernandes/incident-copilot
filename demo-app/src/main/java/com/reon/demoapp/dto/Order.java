package com.reon.demoapp.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Order(
        Long id,
        String item,
        BigDecimal amount,
        String status
) {
}
