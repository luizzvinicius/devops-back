package com.luiz.devops.services;

import com.luiz.devops.dtos.auth.ResponseLoginDto;
import com.luiz.devops.feignclients.LoginClient;
import com.luiz.devops.feignclients.LogoutClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final LoginClient loginClient;
    private final LogoutClient logoutClient;
    @Value("${app.keycloak.admin.clientId}")
    private String clientId;

    public AuthService(LoginClient loginClient, LogoutClient logoutClient) {
        this.loginClient = loginClient;
        this.logoutClient = logoutClient;
    }

    public ResponseLoginDto loginUser(String email, String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", this.clientId);
        formParams.put("grant_type", "password");
        formParams.put("username", email);
        formParams.put("password", password);

        return loginClient.loginUser(formParams);
    }

    public void logoutUser(String id, String token) {
        logoutClient.logoutUser(id, token);
    }
}