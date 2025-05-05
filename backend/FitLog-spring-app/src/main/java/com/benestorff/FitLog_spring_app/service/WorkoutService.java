package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.Workout;
import com.benestorff.FitLog_spring_app.repository.WorkoutRepository;
import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    public Workout createWorkout(Long userId, Workout workout) {
        User user = userRepository.findById(userId)
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

    // Outros métodos conforme necessário
}
