package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
