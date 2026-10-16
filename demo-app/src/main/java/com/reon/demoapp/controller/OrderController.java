package com.reon.demoapp.controller;

import com.reon.demoapp.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    private final Map<Long, Order> orders = new HashMap<>();

    public OrderController() {
        for (long i = 1; i <= 100; i++) {
            orders.put(i, Order.builder()
                    .id(i)
                    .item("Item " + i)
                    .amount(BigDecimal.valueOf(i * 10))
                    .status("CONFIRMED")
                    .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> fetchOrder(@PathVariable Long id) {
        log.info("fetching order for id: {}", id);

        Order order = orders.get(id);
        // Unknown ids leave order as null, so this throws a real NullPointerException
        log.info("order {} has status: {}", id, order.status());

        return ResponseEntity.ok(order);
    }
}
