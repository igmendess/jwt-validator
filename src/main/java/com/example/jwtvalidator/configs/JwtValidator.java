package com.example.jwtvalidator.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtValidator {

    @Value("${jwt.secretkey}")
    private String secretKey;

    private static final Logger logger = LoggerFactory.getLogger(JwtValidator.class);

    public boolean validateJwt(String jwtString) {
        byte[] secret = Base64.getDecoder().decode(secretKey);

        try {
/*          Trecho que irá ignorar a necessidade da secret do token

            String[] split_string = jwtString.split("\\.");
            String base64EncodedBody = split_string[1];

            Base64.Decoder base64Url = Base64.getUrlDecoder();
            Map<String, String> claims = new ObjectMapper().readValue(base64Url.decode(base64EncodedBody), HashMap.class);
*/

            Jwt<?, Claims> jwt =  Jwts.parser().setSigningKey(secret).build().parseSignedClaims(jwtString);
            Claims claims = jwt.getPayload();

            logger.info("JWT parseado com sucesso: {}", jwtString);

            if (claims.size() != 3) {
                logger.warn("Abrindo o JWT, foi encontrado mais de 3 claims.");
                return false;
            }

            String name = claims.get("Name", String.class);
            if (name.length() > 256 || containsNumbers(name)) {
                logger.warn("Claim 'Name' inválida");
                return false;
            }

            String role = claims.get("Role", String.class);
            if (!isValidRole(role)) {
                logger.warn("Claim 'Role' inválida");
                return false;
            }

            String seed = claims.get("Seed", String.class);
            if (!isPrimeNumber(seed)) {
                logger.warn("Claim 'Seed' não é um número primo");
                return false;
            }

            logger.info("JWT validado, as informações contidas atendem aos requisitos");
            return true;
        } catch (MalformedJwtException e) {
            logger.error("JWT inválido: {}", jwtString);
            return false;
        } catch (Exception e) {
            logger.error("Erro ao validar JWT: {}", e.getMessage());
            return false;
        }
    }

    private boolean containsNumbers(String input) {
        return input.matches(".\\d.");
    }

    private boolean isValidRole(String role) {
        return role != null && (role.equals("Admin") || role.equals("Member") || role.equals("External"));
    }

    private boolean isPrimeNumber(String number) {
        int num = Integer.parseInt(number);
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
