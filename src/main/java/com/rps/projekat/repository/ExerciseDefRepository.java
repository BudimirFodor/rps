package com.rps.projekat.repository;

import com.rps.projekat.entity.ExerciseDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciseDefRepository extends JpaRepository<ExerciseDef, Integer> {

    @Query(value = "SELECT insert_exercise_def(?, ?, ?, ?, ?)", nativeQuery = true)
    void insert(String name, int exerciseType, String description, String createdBy, Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exercise_def SET name = ?, exercise_type = ?, description = ?, updated_on = current_timestamp WHERE id = ? AND status = 101", nativeQuery = true)
    int update(String name, int exerciseType, String description, Long exerciseDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exercise_def SET status = 100 WHERE id = ?", nativeQuery = true)
    int publish(Long exerciseDefId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exercise_def SET status = 103 WHERE id = ?", nativeQuery = true)
    int delete(Long exerciseDefId);

    @Query(value = "SELECT ed.* FROM exercise_def ed JOIN party_exercise_def ped ON (ped.exercise_def_id = ed.id) " +
            "WHERE ed.status != 103 AND ped.party_id = ? ORDER BY id DESC", nativeQuery = true)
    List<ExerciseDef> getAll(Long partyId);

    @Query(value = "SELECT ed.* FROM exercise_def ed JOIN training_exercise_defs ted ON (ted.exercise_def_id = ed.id) WHERE ted.training_def_id = ?", nativeQuery = true)
    List<ExerciseDef> getAllForTraining(Long trainingDefId);

    @Query(value = "SELECT ed.* FROM exercise_def ed WHERE ed.status = 100 AND NOT EXISTS " +
            "(SELECT * FROM training_exercise_defs WHERE training_def_id = ? AND exercise_def_id = ed.id)", nativeQuery = true)
    List<ExerciseDef> getAvailable(Long trainingDefId);

    @Query(value = "SELECT add_exercise_def_to_party(?, ?, false)", nativeQuery = true)
    void share(Long partyId, Long exerciseDefId);

}
