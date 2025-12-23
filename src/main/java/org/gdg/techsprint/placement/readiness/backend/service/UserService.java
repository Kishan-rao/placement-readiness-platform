package org.gdg.techsprint.placement.readiness.backend.service;

import org.gdg.techsprint.placement.readiness.backend.dto.LoginRequest;
import org.gdg.techsprint.placement.readiness.backend.dto.RegisterRequest;
import org.gdg.techsprint.placement.readiness.backend.dto.UserResponse;
import org.gdg.techsprint.placement.readiness.backend.entity.User;
import org.gdg.techsprint.placement.readiness.backend.exception.EmailAlreadyExistsException;
import org.gdg.techsprint.placement.readiness.backend.exception.InvalidCredentialsException;
import org.gdg.techsprint.placement.readiness.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = new User(
                "User",                     // default name
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                "USER"                      // default role
        );

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole()
        );
    }

    public UserResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
