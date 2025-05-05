package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.Exercise;
import com.benestorff.FitLog_spring_app.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // Outros métodos conforme necessário
}
