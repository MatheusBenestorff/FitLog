package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.Exercise;
import com.benestorff.FitLog_spring_app.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getAll() {
        return exerciseService.getAllExercises(); 
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise); 
    }
}
