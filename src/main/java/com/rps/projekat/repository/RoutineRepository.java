package com.rps.projekat.repository;

import com.rps.projekat.dto.RoutineDTO;
import com.rps.projekat.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Query(name = "getRoutines", nativeQuery = true)
    List<RoutineDTO> getAll(@Param("partyId") Long partyId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO routine (id, routine_def_id, party_id) VALUES (nextval('routine_seq'), ?, ?)", nativeQuery = true)
    int subscribe(Long routineDefId, Long partyId);

}
