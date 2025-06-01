package com.benestorff.FitLog_spring_app.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String muscleGroup;

    @Column
    @Builder.Default               
    private boolean isSelected = false;


}

