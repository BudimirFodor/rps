package com.rps.projekat.repository;

import com.rps.projekat.dto.RoutineDefDTO;
import com.rps.projekat.entity.RoutineDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoutineDefRepository extends JpaRepository<RoutineDef, Integer> {

    @Transactional
    @Query(value = "SELECT insert_routine_def(?, ?, ?, ?)", nativeQuery = true)
    int insert(String name, String  description, String createdBy, Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE routine_def SET name = ?, description = ?, updated_on = current_timestamp WHERE id =? AND status = 101", nativeQuery = true)
    int update(String name, String description, Long routineDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE routine_def SET status = 100 WHERE id = ?", nativeQuery = true)
    int publish(Long routineDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE routine_def SET status = 103 WHERE id = ?", nativeQuery = true)
    int delete(Long routineDefId);

    @Query(value = "SELECT * FROM share_routine_def(?, ?)", nativeQuery = true)
    void share(Long partyId, Long routineDefId);

    @Query(value = "SELECT rd.* FROM routine_def rd JOIN party_routine_def prd ON (prd.routine_def_id = rd.id)" +
            "WHERE rd.status != 103 AND prd.party_id = ? ORDER BY id DESC", nativeQuery = true)
    List<RoutineDef> getAll(Long partyId);

    @Query(value = "SELECT rd.* FROM routine_def rd JOIN party_routine_def prd ON (prd.routine_def_id = rd.id)" +
            "WHERE rd.status != 103 AND prd.party_id = ?  AND NOT EXISTS " +
            "(SELECT * FROM routine WHERE routine_def_id = rd.id AND party_id = prd.party_id)", nativeQuery = true)
    List<RoutineDef> getAvailable(Long partyId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO routine_training_defs (routine_def_id, training_def_id, day_of_week) VALUES (?, ?, ?)", nativeQuery = true)
    int addTrainingDef(Long routineDefId, Long trainingDefId, int dayOfWeek);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM routine_training_defs WHERE routine_def_id = ? AND training_def_id = ? AND day_of_week = ?", nativeQuery = true)
    int removeTrainingDef(Long routineDefId, Long trainingDefId, int dayOfWeek);

}
