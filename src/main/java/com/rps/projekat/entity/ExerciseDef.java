package com.rps.projekat.entity;

import com.rps.projekat.EntityStatus;
import com.rps.projekat.EntityStatusConverter;
import com.rps.projekat.ExerciseType;
import com.rps.projekat.ExerciseTypeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise_def")
public class ExerciseDef {

    @Id
    private Long id;
    private String name;
    private String description;
    @Convert(converter = ExerciseTypeConverter.class)
    private ExerciseType exerciseType;
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;
    private String createdBy;
    private String createdOn;
    private String updatedOn;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
}
