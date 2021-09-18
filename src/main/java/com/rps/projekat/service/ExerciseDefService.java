package com.rps.projekat.service;

import com.rps.projekat.ExerciseType;
import com.rps.projekat.dto.ExerciseDefDTO;
import com.rps.projekat.entity.ExerciseDef;
import com.rps.projekat.repository.ExerciseDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseDefService {

    @Autowired
    ExerciseDefRepository exerciseDefRepository;

    public void insert(ExerciseDefDTO exerciseDefDTO) throws Exception {
        try {
            exerciseDefRepository.insert(exerciseDefDTO.getName(), exerciseDefDTO.getExerciseType().getCode(), exerciseDefDTO.getDescription(),
                    exerciseDefDTO.getCreatedBy(), exerciseDefDTO.getPartyId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to insert exercise definition");
        }
    }

    public int update(ExerciseDef exerciseDef) throws Exception {
        try {
            return exerciseDefRepository.update(exerciseDef.getName(), exerciseDef.getExerciseType().getCode(), exerciseDef.getDescription(), exerciseDef.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to update exercise definition " + exerciseDef.getId());
        }
    }

    public int publish(Long exerciseDefId) throws Exception {
        try {
            return exerciseDefRepository.publish(exerciseDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to publish exercise definition " + exerciseDefId);
        }
    }

    public int delete(Long exerciseDefId) throws Exception {
        try {
            return exerciseDefRepository.delete(exerciseDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to delete exercise definition " + exerciseDefId);
        }
    }

    public List<ExerciseDef> getAll(Long partyId) throws Exception {
        try {
            return exerciseDefRepository.getAll(partyId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get exercise definitions");
        }
    }

    public List<ExerciseDef> getAvailable(Long trainingDefId) throws Exception {
        try {
            return exerciseDefRepository.getAvailable(trainingDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get active exercise definitions");
        }
    }

    public void share(Long partyId, Long exerciseDefId) throws Exception {
        try {
            exerciseDefRepository.share(partyId, exerciseDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to share exercise definition " + exerciseDefId + " to party " + partyId);
        }
    }
}
