package org.gdg.techsprint.placement.readiness.backend.controller;

import org.gdg.techsprint.placement.readiness.backend.dto.LoginRequest;
import org.gdg.techsprint.placement.readiness.backend.dto.RegisterRequest;
import org.gdg.techsprint.placement.readiness.backend.dto.UserResponse;
import org.gdg.techsprint.placement.readiness.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
