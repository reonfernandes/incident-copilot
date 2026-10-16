package com.reon.demoapp.controller;

import com.reon.demoapp.exception.DatabaseConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chaos")
@Slf4j
public class ChaosController {

    @GetMapping("/db-down")
    public void databaseDown() {
        log.info("simulating database outage");
        throw new DatabaseConnectionException(
                "Unable to acquire JDBC Connection: HikariPool-1 - Connection is not available, request timed out after 30000ms");
    }
}
