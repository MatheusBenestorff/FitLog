package com.benestorff.FitLog_spring_app.controller;


import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.network.LoginRequest;
import com.benestorff.FitLog_spring_app.network.LoginResponse;
import com.benestorff.FitLog_spring_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                // Login bem-sucedido
                return ResponseEntity.ok(new LoginResponse("login-sucesso"));
            }
        }

        // Falha no login
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
