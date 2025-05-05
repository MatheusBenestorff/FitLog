package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.Workout;
import com.benestorff.FitLog_spring_app.repository.WorkoutRepository;
import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.repository.UserRepository;
import com.benestorff.FitLog_spring_app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    public Workout createWorkout(Long userId, Workout workout) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);
    }


}
