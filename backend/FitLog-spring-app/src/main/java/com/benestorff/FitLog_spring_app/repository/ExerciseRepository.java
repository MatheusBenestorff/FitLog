package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Override
    List<Exercise> findAll();
}
