package com.example.fitlog.models;

import java.time.LocalDate;
import java.util.List;

public class Workout {
    private Long id;
    private String name;
    private String description;
    private String date;
    private User user;
    private List<Exercise> exercises;


    public Workout(String name, String description, String date, User user, List<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.user = user;
        this.exercises = exercises;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public User getUser(){
        return user;
    }

    public List<Exercise> getExercises(){
        return exercises;
    }
}
