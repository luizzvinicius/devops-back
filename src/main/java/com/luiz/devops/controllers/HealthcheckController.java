package com.luiz.devops.controllers;

import com.luiz.devops.dtos.HealthcheckDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthcheckController {

    @Value("${spring.application.version}")
    private String version;

    @GetMapping
    public ResponseEntity<HealthcheckDto> healthcheck() {
        return ResponseEntity.status(HttpStatus.OK).body(new HealthcheckDto("up", version));
    }
}