package com.example.fitlog.models;

import java.util.List;

public class Exercise {
    private Long id;
    private String name;
    private String muscleGroup;
    private String equipment;
    private String instructions;

    public Exercise(String name, String muscleGroup, String equipment, String instructions) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.instructions = instructions;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getInstructions(){
        return instructions;
    }

}
