package com.benestorff.FitLog_spring_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workout_session_sets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSessionSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}
