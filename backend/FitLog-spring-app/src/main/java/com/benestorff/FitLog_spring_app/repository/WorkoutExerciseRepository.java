package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
}
