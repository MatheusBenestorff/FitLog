package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.network.LoginRequest;
import com.benestorff.FitLog_spring_app.network.LoginResponse;
import com.benestorff.FitLog_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new LoginResponse("login-sucesso"));
            }
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
