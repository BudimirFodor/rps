package com.rps.projekat.dto;

import com.rps.projekat.ExerciseType;
import com.rps.projekat.entity.ExerciseSet;

import java.util.List;

public class ExerciseDTO {

    private Long id;
    private String name;
    private ExerciseType exerciseType;
    private String description;
    private List<ExerciseSet> exerciseSets;

    public ExerciseDTO(Long id, String name, int exerciseType, String description) {
        this.id = id;
        this.name = name;
        this.exerciseType = ExerciseType.get(exerciseType);
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
