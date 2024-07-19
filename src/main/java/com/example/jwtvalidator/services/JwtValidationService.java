package com.example.jwtvalidator.services;

import com.example.jwtvalidator.components.JwtValidatorComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtValidationService {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationService.class);

    private final JwtValidatorComponent jwtValidatorComponent;

    @Autowired
    public JwtValidationService(JwtValidatorComponent jwtValidatorComponent) {
        this.jwtValidatorComponent = jwtValidatorComponent;
    }

    public boolean validateJwt(String jwtString) {
        logger.info("Validando JWT: {}", jwtString);
        return jwtValidatorComponent.validateJwt(jwtString);
    }
}