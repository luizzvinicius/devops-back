package com.luiz.devops.services;

import com.luiz.devops.dtos.auth.ResponseLoginDto;
import com.luiz.devops.feignclients.LoginClient;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final Keycloak keycloak;
    private final LoginClient loginClient;
    @Value("${app.keycloak.realm}")
    private String realm;
    @Value("${app.keycloak.admin.clientId}")
    private String clientId;

    public AuthService(Keycloak keycloak, LoginClient loginClient) {
        this.keycloak = keycloak;
        this.loginClient = loginClient;
    }

    public ResponseLoginDto loginUser(String email, String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", clientId);
        formParams.put("grant_type", "password");
        formParams.put("username", email);
        formParams.put("password", password);

        return loginClient.loginUser(formParams);
    }

    public void logoutUser(String id) {
        keycloak.realm(realm)
                .users().get(id).getUserSessions()
                .forEach(session -> keycloak.realm(realm).deleteSession(session.getId(), false));
    }
}