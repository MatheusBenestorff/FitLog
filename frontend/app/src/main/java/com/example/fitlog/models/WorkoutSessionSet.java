package com.example.fitlog.models;

import java.util.List;

public class WorkoutSessionSet {
    private Long id;
    private Integer reps;
    private Double weight;
    private Integer setNumber;
    private WorkoutSession workoutSession;
    private Exercise exercise;

    public WorkoutSessionSet(Integer reps, Double weight, Integer setNumber, WorkoutSession workoutSession, Exercise exercise) {
        this.reps = reps;
        this.weight = weight;
        this.setNumber = setNumber;
        this.workoutSession = workoutSession;
        this.exercise = exercise;
    }

    public Long getId() {
        return id;
    }

    public Integer getReps() {
        return reps;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    public Exercise getExercise(){
        return exercise;
    }

}
