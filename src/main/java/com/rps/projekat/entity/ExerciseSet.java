package com.rps.projekat.entity;

import com.rps.projekat.EntityStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise_set")
public class ExerciseSet {

    @Id
    private Long id;
    private Long exerciseId;
    private Long numberOfReps;
    private double distanceValue;
    private double weightValue;
    private double timeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(Long numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public double getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(double distanceValue) {
        this.distanceValue = distanceValue;
    }

    public double getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(double weightValue) {
        this.weightValue = weightValue;
    }

    public double getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(double timeValue) {
        this.timeValue = timeValue;
    }
}
