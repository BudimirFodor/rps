package com.rps.projekat.service;

import com.rps.projekat.dto.CreateTrainingDTO;
import com.rps.projekat.dto.TrainingDTO;
import com.rps.projekat.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    public int insert(CreateTrainingDTO createTrainingDTO) throws Exception {
        try {
            return trainingRepository.insert(createTrainingDTO.getTrainingDefId(), createTrainingDTO.getPartyId(), createTrainingDTO.getRoutineId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to create training ");
        }
    }

    public int complete(Long trainingId) throws Exception {
        try {
            return trainingRepository.complete(trainingId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to complete training " + trainingId);
        }
    }

    public List<TrainingDTO> getForRoutineMonth(Long routineId, String fromDateStr, String toDateStr) throws Exception {
        try {
            return trainingRepository.getForRoutineMonth(routineId, fromDateStr, toDateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get trainings for routine " + routineId);
        }
    }
}
