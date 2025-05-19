package com.example.fitlog.models;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String birthDate;
    private String gender;
    private Double height;
    private Double weight;
    private List<Workout> workouts;

    public User(String name, String email, String password, String birthDate, String gender, Double height,
                Double weight, List<Workout> workouts) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.workouts = workouts;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public List<Workout> getWorkouts(){
        return workouts;
    }

}


