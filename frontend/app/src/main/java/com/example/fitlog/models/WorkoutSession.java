package com.example.fitlog.models;

import java.util.List;

public class WorkoutSession {
    private Long id;
    private String startTime;
    private String endTime;
    private User user;
    private Workout workout;
    private List<WorkoutSessionSet> sets;

    public WorkoutSession(String startTime, String endTime, User user, Workout workout, List<WorkoutSessionSet> sets) {
        this.startTime = startTime;
        this.endTime = endTime
        this.user = user;
        this.workout = workout;
        this.sets = sets;
    }


    public Long getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public User getUser) {
        return user;
    }

    public Workout getWorkout() {
        return workout;
    }


    public List<WorkoutSessionSet> getSets(){
        return sets;
    }

}
