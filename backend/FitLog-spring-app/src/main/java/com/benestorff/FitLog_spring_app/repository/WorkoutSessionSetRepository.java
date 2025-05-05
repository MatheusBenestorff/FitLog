package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.WorkoutSessionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutSessionSetRepository extends JpaRepository<WorkoutSessionSet, Long> {
    List<WorkoutSessionSet> findByWorkoutSessionId(Long workoutSessionId);
}
