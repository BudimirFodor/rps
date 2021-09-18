package com.rps.projekat.entity;

import com.rps.projekat.dto.ExerciseDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@NamedNativeQuery(
        name = "getExercises",
        query ="SELECT e.id, ed.name, ed.exercise_type, ed.description FROM exercise e JOIN exercise_def ed ON (ed.id = e.exercise_def_id) " +
                "WHERE training_id = :trainingId",
        resultSetMapping = "ExerciseDTOMapper"
)
@SqlResultSetMapping(
        name = "ExerciseDTOMapper",
        classes = @ConstructorResult(
                targetClass = ExerciseDTO.class,
                columns = { @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "exercise_type", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class)}))
@Table(name = "exercise")
public class Exercise {

    @Id
    private Long id;
    private Long exerciseDefId;
    private Long partyId;
    private Long trainingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseDefId() {
        return exerciseDefId;
    }

    public void setExerciseDefId(Long exerciseDefId) {
        this.exerciseDefId = exerciseDefId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }
}
