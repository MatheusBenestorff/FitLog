package com.example.fitlog.models;

import java.io.Serializable;
import java.util.List;

public class Exercise implements Serializable {
    private Long id;
    private String name;
    private String muscleGroup;
    private boolean isSelected = false;

    public Exercise() {
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



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
