package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.model.Workout;
import com.benestorff.FitLog_spring_app.network.LoginRequest;
import com.benestorff.FitLog_spring_app.service.UserService;
import com.benestorff.FitLog_spring_app.service.WorkoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.benestorff.FitLog_spring_app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WorkoutService workoutService;
    

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email e senha são obrigatórios");
            }
            
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email já cadastrado");
            }
            
            User savedUser = userService.save(user);
            return ResponseEntity.ok(savedUser);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(user); 
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
    
    @GetMapping("/{userId}/workouts")
    public List<Workout> getUserWorkouts(@PathVariable Long userId) {
        return workoutService.getWorkoutsByUser(userId);
    }

    @PostMapping("/{userId}/workouts")
    public Workout createUserWorkout(@PathVariable Long userId, @RequestBody Workout workout) {
        return workoutService.createWorkout(userId, workout);
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication auth) {
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
    
        Optional<User> userOpt = userService.findByEmail(auth.getName());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    
        return ResponseEntity.ok(userOpt.get());
    }
    
}
