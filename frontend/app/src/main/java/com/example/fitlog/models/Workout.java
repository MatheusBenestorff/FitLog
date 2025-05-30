package com.example.fitlog.models;

import java.io.Serializable;
import java.util.List;

public class Workout implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String date;  // mantém como String
    private User user;    // só usar se for obrigatório
    private List<Exercise> exercises;

    public Workout() {}  // Necessário para Retrofit

    public Workout(String name, String description, String date, User user, List<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.user = user;
        this.exercises = exercises;
    }

    // Getters e setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setUser(User user) { this.user = user; }
    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public User getUser(){ return user; }
    public List<Exercise> getExercises(){ return exercises; }
}
