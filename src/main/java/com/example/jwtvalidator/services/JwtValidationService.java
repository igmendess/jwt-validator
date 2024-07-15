package com.example.jwtvalidator.services;

import com.example.jwtvalidator.configs.JwtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JwtValidationService {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationService.class);

    private final JwtValidator jwtValidator;

    @Autowired
    public JwtValidationService(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    public boolean validateJwt(String jwtString) {
        logger.info("Validando JWT: {}", jwtString);
        return jwtValidator.validateJwt(jwtString);
    }
}