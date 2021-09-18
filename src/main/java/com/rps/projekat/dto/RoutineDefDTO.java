package com.rps.projekat.dto;

import com.rps.projekat.entity.RoutineDef;
import com.rps.projekat.entity.TrainingDef;

import java.util.List;

public class RoutineDefDTO extends RoutineDef {

    private Long partyId;
    private List<TrainingDefDTO> trainingDefs;

    public RoutineDefDTO() {
    }

    public RoutineDefDTO(RoutineDef routineDef) {
        super(routineDef.getId(), routineDef.getName(), routineDef.getDescription(), routineDef.getStatus(),
                routineDef.getCreatedBy(), routineDef.getCreatedOn(), routineDef.getUpdatedOn());
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public List<TrainingDefDTO> getTrainingDefs() {
        return trainingDefs;
    }

    public void setTrainingDefs(List<TrainingDefDTO> trainingDefs) {
        this.trainingDefs = trainingDefs;
    }
}
