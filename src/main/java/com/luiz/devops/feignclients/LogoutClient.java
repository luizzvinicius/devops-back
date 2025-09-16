package com.luiz.devops.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "logoutClient", url = "${app.keycloak.serverUrl}/admin/realms/${app.keycloak.realm}/users")
public interface LogoutClient {
    @PostMapping("/{id}/logout")
    void logoutUser(@PathVariable("id") String id, @RequestHeader("Authorization") String bearerToken);
}