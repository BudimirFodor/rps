package com.rps.projekat.dto;

public class TrainingDTO {

    private Long id;
    private Long trainingDefId;
    private String startedOn;
    private String finishedOn;
    private String name;
    private String description;
    private String createdBy;

    public TrainingDTO() {
    }

    public TrainingDTO(Long id, Long trainingDefId, String startedOn, String finishedOn, String name, String description, String createdBy) {
        this.id = id;
        this.trainingDefId = trainingDefId;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainingDefId() {
        return trainingDefId;
    }

    public void setTrainingDefId(Long trainingDefId) {
        this.trainingDefId = trainingDefId;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(String finishedOn) {
        this.finishedOn = finishedOn;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
