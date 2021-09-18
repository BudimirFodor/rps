package com.rps.projekat.repository;

import com.rps.projekat.dto.TrainingDefDTO;
import com.rps.projekat.entity.TrainingDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TrainingDefRepository extends JpaRepository<TrainingDef, Integer> {

    @Transactional
    @Query(value = "SELECT insert_training_def(?, ?, ?, ?)", nativeQuery = true)
    void insert(String name, String  description, String createdBy, Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE training_def SET name = ?, description = ?, updated_on = current_timestamp WHERE id =? AND status = 101", nativeQuery = true)
    int update(String name, String description, Long trainingDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE training_def SET status = 100 WHERE id = ?", nativeQuery = true)
    int publish(Long trainingDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE training_def SET status = 103 WHERE id = ?", nativeQuery = true)
    int delete(Long trainingDefId);

    @Query(value = "SELECT * FROM share_training_def(?, ?)", nativeQuery = true)
    void share(Long partyId, Long trainingDefId);

    @Query(value = "SELECT td.* FROM training_def td JOIN party_training_def ptd ON (ptd.training_def_id = td.id)" +
            "WHERE td.status != 103 AND ptd.party_id = ? ORDER BY id DESC", nativeQuery = true)
    List<TrainingDef> getAll(Long partyId);

    @Query(name = "getAvailableTrainingDefs", nativeQuery = true)
    List<TrainingDefDTO> getAllForRoutine(@Param("routineDefId") Long routineDefId);

    @Query(value = "SELECT td.* FROM training_def td WHERE td.status = 100 AND NOT EXISTS " +
            "(SELECT * FROM routine_training_defs WHERE routine_def_id = ? AND training_def_id = td.id)", nativeQuery = true)
    List<TrainingDef> getAvailable(Long routineDefId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO training_exercise_defs (training_def_id, exercise_def_id) VALUES (?, ?)", nativeQuery = true)
    int addExerciseDef(Long trainingDefId, Long exerciseDefId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM training_exercise_defs WHERE training_def_id = ? AND exercise_def_id = ?", nativeQuery = true)
    int removeExerciseDef(Long trainingDefId, Long exerciseDefId);

}
