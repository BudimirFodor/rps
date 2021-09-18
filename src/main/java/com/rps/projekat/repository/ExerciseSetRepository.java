package com.rps.projekat.repository;

import com.rps.projekat.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Integer> {

    // TODO CHECK DOUBLE MAPPING WITH HIBERNATE
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO exercise_set (id, exercise_id, number_of_reps, distance_value, weight_value, time_value, status)" +
            "VALUES (nextval('exercise_set_seq'), ?, ?, ?, ?, ?, 100)", nativeQuery = true)
    int insert(Long exerciseId, Long numberOfReps, double distanceValue, double weightValue, double timeValue);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exercise_set SET status = 103 WHERE id = ?", nativeQuery = true)
    int delete(Long exerciseSetId);

    @Query(value = "SELECT * FROM exercise_set WHERE status != 103 AND exercise_id = ?", nativeQuery = true)
    List<ExerciseSet> getAll(Long exerciseId);

}
