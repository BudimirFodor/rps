package com.rps.projekat.entity;

import com.rps.projekat.dto.RoutineDTO;
import com.rps.projekat.dto.TrainingDefDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "routine")
@NamedNativeQuery(
        name = "getRoutines",
        query ="SELECT r.*, rd.name, rd.description, rd.status, rd.created_by, rd.created_on, rd.updated_on FROM routine_def rd " +
                "JOIN party_routine_def prd ON (prd.routine_def_id = rd.id) JOIN routine r ON (r.routine_def_id = rd.id AND r.party_id = prd.party_id) " +
                "WHERE rd.status != 103 AND prd.party_id = :partyId",
        resultSetMapping = "RoutineDTOMapper"
)
@SqlResultSetMapping(
        name = "RoutineDTOMapper",
        classes = @ConstructorResult(
                targetClass = RoutineDTO.class,
                columns = { @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "routine_def_id", type = Long.class),
                        @ColumnResult(name = "party_id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "created_by", type = String.class),
                        @ColumnResult(name = "created_on", type = String.class),
                        @ColumnResult(name = "updated_on", type = String.class)}))
public class Routine {

    @Id
    private Long id;
    private Long routineDefId;
    private Long partyId;

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
}
