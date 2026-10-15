package com.reon.demoapp.controller;

import com.reon.demoapp.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    @GetMapping("/{id}")
    public ResponseEntity<Void> fetchOrder (@PathVariable Long id) {
        log.info("fetching order for id: {}", id);

        Map<Long, Order> ids = new HashMap<>();

        for (long i = 0; i <= 100; i++) {
            ids.put(i, null);
        }

        if (ids.containsKey(id)) {
            ids.get(id);
        }

        return null;
    }
}
