package com.example.jwtvalidator.controllers;

import com.example.jwtvalidator.services.JwtValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
public class JwtValidationController {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationController.class);

    private final JwtValidationService jwtValidationService;

    @Autowired
    public JwtValidationController(JwtValidationService jwtValidationService) {
        this.jwtValidationService = jwtValidationService;
    }

    @GetMapping("/validate-jwt")
    public ResponseEntity<String> validateJwt(@RequestParam String jwt) {
        logger.info("Endpoint /validate-jwt chamado com JWT: {}", jwt);
        boolean isValid = jwtValidationService.validateJwt(jwt);
        if (isValid) {
            return ResponseEntity.ok("JWT válido");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT inválido");
        }
    }
}
