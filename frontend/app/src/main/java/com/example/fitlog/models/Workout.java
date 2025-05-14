package com.example.fitlog.models;

public class Workout {
    private Long id;
    private String name;
    private String description;


    public Workout() {}

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
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
}
