package org.ies.deti.ua.medisync.controller;

import java.util.HashMap;
import java.util.Map;

import org.ies.deti.ua.medisync.dto.LoginRequest;
import org.ies.deti.ua.medisync.model.User;
import org.ies.deti.ua.medisync.security.JwtUtil;
import org.ies.deti.ua.medisync.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}