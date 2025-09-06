package com.luiz.devops.controllers;

import com.luiz.devops.dtos.auth.LoginDto;
import com.luiz.devops.dtos.auth.ResponseLoginDto;
import com.luiz.devops.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> loginUser(@RequestBody LoginDto loginDto) {
        var loginResponse = authService.loginUser(loginDto.email(), loginDto.password());
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logoutUser(@PathVariable String id) {
        authService.logoutUser((id));
        return ResponseEntity.ok().build();
    }
}
