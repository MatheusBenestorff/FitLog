package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
}
