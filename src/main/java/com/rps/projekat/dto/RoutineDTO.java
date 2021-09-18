package com.rps.projekat.dto;

import com.rps.projekat.EntityStatus;

import java.util.List;

public class RoutineDTO {

    private Long id;
    private Long routineDefId;
    private Long partyId;
    private String name;
    private String description;
    private EntityStatus status;
    private String createdBy;
    private String createdOn;
    private String updatedOn;
    private List<TrainingDefDTO> trainingDefs;

    public RoutineDTO() {
    }

    public RoutineDTO(Long id, Long routineDefId, Long partyId, String name, String description,
                      int status, String createdBy, String createdOn, String updatedOn) {
        this.id = id;
        this.routineDefId = routineDefId;
        this.partyId = partyId;
        this.name = name;
        this.description = description;
        this.status = EntityStatus.get(status);
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoutineDefId() {
        return routineDefId;
    }

    public void setRoutineDefId(Long routineDefId) {
        this.routineDefId = routineDefId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
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

    public List<TrainingDefDTO> getTrainingDefs() {
        return trainingDefs;
    }

    public void setTrainingDefs(List<TrainingDefDTO> trainingDefs) {
        this.trainingDefs = trainingDefs;
    }
}
