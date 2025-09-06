package com.luiz.devops.configuration.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        return new JwtAuthenticationToken(
                jwt,
                jwt.getClaim("sub")
        );
    }

    public Map<String, Object> getCustomClaims(Jwt jwt) {
        UUID id = UUID.fromString(jwt.getClaim("sub"));
        boolean isEmailVerified = jwt.getClaim("email_verified");
        String email = jwt.getClaim("email");
        return Map.of("id", id, "email", email, "isEmailVerified", isEmailVerified);
    }
}