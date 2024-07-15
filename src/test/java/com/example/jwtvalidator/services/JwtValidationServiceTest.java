package com.example.jwtvalidator.services;

import com.example.jwtvalidator.configs.JwtValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class JwtValidationServiceTest {

    @Mock
    private JwtValidator jwtValidator;

    @InjectMocks
    private JwtValidationService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("JWT válida. Deve retornar sucesso (true)")
    void validateJwt() {
        when(this.service.validateJwt(anyString())).thenReturn(Boolean.TRUE);
        Boolean response = this.service.validateJwt(anyString());
        assertEquals(response.getClass(), Boolean.class);
        assertTrue(response);
    }

    @Test
    @DisplayName("JWT inválida. Deve retornar falha (false)")
    void validateJwt2() {
        when(this.service.validateJwt(anyString())).thenReturn(Boolean.FALSE);
        Boolean response = this.service.validateJwt(anyString());
        assertEquals(response.getClass(), Boolean.class);
        assertFalse(response);
    }
}