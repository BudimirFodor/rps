package com.rps.projekat.entity;

import com.rps.projekat.EntityStatus;
import com.rps.projekat.EntityStatusConverter;
import com.rps.projekat.dto.TrainingDefDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="training_def")
@NamedNativeQuery(
        name = "getAvailableTrainingDefs",
        query ="SELECT td.*, rtd.day_of_week FROM training_def td JOIN routine_training_defs rtd ON (rtd.training_def_id = td.id) " +
                "WHERE rtd.routine_def_id = :routineDefId",
        resultSetMapping = "TrainingDefDTOMapper"
)
@SqlResultSetMapping(
        name = "TrainingDefDTOMapper",
        classes = @ConstructorResult(
                targetClass = TrainingDefDTO.class,
                columns = { @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "created_by", type = String.class),
                        @ColumnResult(name = "created_on", type = String.class),
                        @ColumnResult(name = "updated_on", type = String.class),
                        @ColumnResult(name = "day_of_week", type = Integer.class)}))
public class TrainingDef {

    @Id
    private Long id;
    private String name;
    private String description;
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;
    private String createdBy;
    private String createdOn;
    private String updatedOn;

    public TrainingDef() {
    }

    public TrainingDef(Long id, String name, String description, EntityStatus status, String createdBy, String createdOn, String updatedOn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
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
}
