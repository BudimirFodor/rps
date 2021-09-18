package com.rps.projekat.dto;

public class CreateTrainingDTO {

    private Long trainingDefId;
    private Long partyId;
    private Long routineId;

    public Long getTrainingDefId() {
        return trainingDefId;
    }

    public void setTrainingDefId(Long trainingDefId) {
        this.trainingDefId = trainingDefId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }
}
