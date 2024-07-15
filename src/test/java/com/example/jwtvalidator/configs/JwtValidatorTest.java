package com.example.jwtvalidator.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtValidatorTest {

    @InjectMocks
    private JwtValidator jwtValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtValidator, "secretKey", "4xdMYfO0rjse/X3wbe7PWoDDKtWnYLZaIhMIYYAFBA4=");
    }

    @Test
    @DisplayName("JWT válida. Deve retornar sucesso (true)")
    void validateJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImV4cCI6IjIwMjQtMDctMjlUMjA6MDA6MDAuMDAwWiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.Hcsgd2SmHlotisYSQvpWrLRV60LeiBo4R16AAJN38do";
        var response = this.jwtValidator.validateJwt(jwt);
        assertTrue(response);
    }

    @Test
    @DisplayName("Quantidade de claims acima do esperado")
    void validateJwt2() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImV4cCI6IjIwMjQtMDctMjlUMjA6MDA6MDAuMDAwWiJ9.eyJSb2xlIjoiQWRtaW4iLCJPcmciOiJCUiIsIlNlZWQiOiI3ODQxIiwiTmFtZSI6IlRvbmluaG8gQXJhdWpvIn0.pfuOgYoWcCY3-9osOABXA-YxbKmUSowEQt4laJRcWM4";
        var response = this.jwtValidator.validateJwt(jwt);
        assertFalse(response);
    }

    @Test
    @DisplayName("Claim Role inválida, valor não reconhecido")
    void validateJwt3() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImV4cCI6IjIwMjQtMDctMjlUMjA6MDA6MDAuMDAwWiJ9.eyJSb2xlIjoiT3RoZXIiLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.2vc8qGfYwjFKB6kT8At1AE6PDIrOcWzs2Z41IUkToqg";
        var response = this.jwtValidator.validateJwt(jwt);
        assertFalse(response);
    }

    @Test
    @DisplayName("Claim Seed inválida")
    void validateJwt4() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImV4cCI6IjIwMjQtMDctMjlUMjA6MDA6MDAuMDAwWiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MiIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.S_z_CPTvsV-FM8pY5jXswdDWI7z91l7tm-vDiQ_Odjo";
        var response = this.jwtValidator.validateJwt(jwt);
        assertFalse(response);
    }

    @Test
    @DisplayName("Claim Name inválida")
    void validateJwt5() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImV4cCI6IjIwMjQtMDctMjlUMjA6MDA6MDAuMDAwWiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5vbWUiOiJUb25pbmhvIEFyYXVqbyJ9.y7egyljhF1oOCEbCS2JYw1CXbl98O09nYH98nLC3M5A";
        var response = this.jwtValidator.validateJwt(jwt);
        assertFalse(response);
    }

    @Test
    @DisplayName("JWT inválida. Deve retornar falha (false)")
    void validateJwt6() {
        String jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\n";
        var response = this.jwtValidator.validateJwt(jwt);
        assertFalse(response);
    }
}