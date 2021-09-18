package com.rps.projekat.repository;

import com.rps.projekat.dto.ExerciseDTO;
import com.rps.projekat.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query(name = "getExercises", nativeQuery = true)
    List<ExerciseDTO> getForTraining(@Param("trainingId") Long trainingId);


}
