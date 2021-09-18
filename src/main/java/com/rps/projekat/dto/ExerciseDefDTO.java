package com.rps.projekat.dto;

import com.rps.projekat.entity.ExerciseDef;

public class ExerciseDefDTO extends ExerciseDef {

    private Long partyId;

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}
