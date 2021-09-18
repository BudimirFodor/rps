package com.rps.projekat.dto;

import com.rps.projekat.EntityStatus;
import com.rps.projekat.entity.ExerciseDef;
import com.rps.projekat.entity.TrainingDef;

import java.util.List;

public class TrainingDefDTO extends TrainingDef {

    private Long partyId;
    private List<ExerciseDef> exerciseDefs;
    private int dayOfWeek;

    public TrainingDefDTO() {
    }

    public TrainingDefDTO(Long id, String name, String description, int status, String createdBy, String createdOn, String updatedOn, int dayOfWeek) {
        super(id, name, description, EntityStatus.get(status), createdBy, createdOn, updatedOn);
        this.dayOfWeek = dayOfWeek;
    }

    public TrainingDefDTO(TrainingDef trainingDef) {
        super(trainingDef.getId(), trainingDef.getName(), trainingDef.getDescription(), trainingDef.getStatus(),
                trainingDef.getCreatedBy(), trainingDef.getCreatedOn(), trainingDef.getUpdatedOn());
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public List<ExerciseDef> getExerciseDefs() {
        return exerciseDefs;
    }

    public void setExerciseDefs(List<ExerciseDef> exerciseDefs) {
        this.exerciseDefs = exerciseDefs;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
