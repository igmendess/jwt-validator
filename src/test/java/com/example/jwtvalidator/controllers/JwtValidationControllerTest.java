package com.example.jwtvalidator.controllers;

import com.example.jwtvalidator.services.JwtValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;

class JwtValidationControllerTest {

    @Mock
    private JwtValidationService jwtValidationService;

    @InjectMocks
    private JwtValidationController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar falha (false)")
    void validateJwt() {
        var response = this.controller.validateJwt(anyString());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("JWT inválido", response.getBody());
    }
}