package com.example.fitlog.models;

import java.util.List;

public class Exercise {
    private Long id;
    private String name;
    private String muscleGroup;
    private String equipment;
    private String instructions;
    private boolean isSelected = false;

    public Exercise(String name, String muscleGroup, String equipment, String instructions, Boolean isSelected) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.instructions = instructions;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
