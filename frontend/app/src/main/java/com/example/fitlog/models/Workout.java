package com.example.fitlog.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Workout implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String date;
    private User user;
    private List<Exercise> exercises;

    public Workout() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Exercise> getExercises() { return exercises; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
