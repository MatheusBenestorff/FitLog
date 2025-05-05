package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.Workout;
import com.benestorff.FitLog_spring_app.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/user/{userId}")
    public List<Workout> getByUserId(@PathVariable Long userId) {
        return workoutService.getWorkoutsByUser(userId); // corrigido
    }

    @PostMapping("/user/{userId}")
    public Workout create(@PathVariable Long userId, @RequestBody Workout workout) {
        return workoutService.createWorkout(userId, workout); // corrigido
    }
}
