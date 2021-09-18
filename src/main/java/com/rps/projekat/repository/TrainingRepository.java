package com.rps.projekat.repository;

import com.rps.projekat.dto.TrainingDTO;
import com.rps.projekat.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    @Transactional
    @Query(value = "SELECT create_training(?, ?, ?)", nativeQuery = true)
    int insert(Long trainingDefId, Long partyId, Long routineId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE training SET finished_on = current_timestamp, status = 100 WHERE id = ?", nativeQuery = true)
    int complete(Long trainingId);

    @Query(name = "getTrainings", nativeQuery = true)
    List<TrainingDTO> getForRoutineMonth(@Param("routineId") Long routineId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
}
