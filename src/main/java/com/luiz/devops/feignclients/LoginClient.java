package com.luiz.devops.feignclients;

import com.luiz.devops.dtos.auth.ResponseLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "login-client", url = "${app.keycloak.serverUrl}/realms/${app.keycloak.realm}/protocol/openid-connect/token")
public interface LoginClient {
    @PostMapping(consumes = "application/x-www-form-urlencoded")
    ResponseLoginDto loginUser(@RequestBody Map<String, ?> formParams);
}