package com.example.jwtvalidator.controllers;

import com.example.jwtvalidator.services.JwtValidationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtValidationController {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationController.class);

    private final JwtValidationService jwtValidationService;

    @Autowired
    public JwtValidationController(JwtValidationService jwtValidationService) {
        this.jwtValidationService = jwtValidationService;
    }

    @GetMapping("/validate-jwt")
    @Tag(name = "Principal", description = "Endpoint responsável pelo recebimento do token para análise de regras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")})
    })
    public ResponseEntity<Boolean> validateJwt(@RequestParam String jwt) {
        logger.info("Endpoint /validate-jwt chamado com JWT: {}", jwt);
        boolean isValid = jwtValidationService.validateJwt(jwt);
        if (isValid) {
            return ResponseEntity.ok(Boolean.TRUE);
        } else {
            return ResponseEntity.ok(Boolean.FALSE);
        }
    }
}
