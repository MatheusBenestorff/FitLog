package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    List<WorkoutSession> findByWorkoutId(Long workoutId);
    List<WorkoutSession> findByUserId(Long userId);
}
