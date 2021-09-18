package com.rps.projekat.entity;

import com.rps.projekat.EntityStatus;
import com.rps.projekat.EntityStatusConverter;
import com.rps.projekat.dto.TrainingDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "training")
@NamedNativeQuery(
        name = "getTrainings",
        query ="SELECT t.id, t.training_def_id, t.started_on, t.finished_on, td.name, td.description, td.created_by FROM training t " +
                "JOIN training_def td ON (td.id = t.training_def_id) " +
                "WHERE t.routine_id = :routineId AND t.started_on >= :dateFrom\\:\\:timestamp AND t.started_on < :dateTo\\:\\:timestamp",
        resultSetMapping = "TrainingDTOMapper"
)
@SqlResultSetMapping(
        name = "TrainingDTOMapper",
        classes = @ConstructorResult(
                targetClass = TrainingDTO.class,
                columns = { @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "training_def_id", type = Long.class),
                        @ColumnResult(name = "started_on", type = String.class),
                        @ColumnResult(name = "finished_on", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "created_by", type = String.class)}))
public class Training {

    @Id
    private Long id;
    private Long trainingDefId;
    private Long partyId;
    private Long routineId;
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;
    private String startedOn;
    private String finishedOn;

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

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
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
}
