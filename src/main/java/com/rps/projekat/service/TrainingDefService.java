package com.rps.projekat.service;

import com.rps.projekat.dto.TrainingDefDTO;
import com.rps.projekat.entity.TrainingDef;
import com.rps.projekat.repository.ExerciseDefRepository;
import com.rps.projekat.repository.TrainingDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingDefService {

    @Autowired
    TrainingDefRepository trainingDefRepository;

    @Autowired
    ExerciseDefRepository exerciseDefRepository;

    public void insert(TrainingDefDTO trainingDefDTO) throws Exception {
        try {
            trainingDefRepository.insert(trainingDefDTO.getName(), trainingDefDTO.getDescription(),
                    trainingDefDTO.getCreatedBy(), trainingDefDTO.getPartyId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to insert training definition");
        }
    }

    public int update(TrainingDef trainingDef) throws Exception {
        try {
            return trainingDefRepository.update(trainingDef.getName(), trainingDef.getDescription(), trainingDef.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to update training definition " + trainingDef.getId());
        }
    }

    public int publish(Long trainingDefId) throws Exception {
        try {
            return trainingDefRepository.publish(trainingDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to publish training definition " + trainingDefId);
        }
    }

    public int delete(Long trainingDefId) throws Exception {
        try {
            return trainingDefRepository.delete(trainingDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to delete training definition " + trainingDefId);
        }
    }

    public List<TrainingDefDTO> getAll(Long partyId) throws Exception {
        try {
            List<TrainingDefDTO> result = new ArrayList<>();
            List<TrainingDef> trainingDefs = trainingDefRepository.getAll(partyId);
            for (TrainingDef trainingDef: trainingDefs) {
                TrainingDefDTO trainingDefDTO = new TrainingDefDTO(trainingDef);
                trainingDefDTO.setExerciseDefs(exerciseDefRepository.getAllForTraining(trainingDef.getId()));
                result.add(trainingDefDTO);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get training definitions");
        }
    }

    public List<TrainingDef> getAvailable(Long trainingDefId) throws Exception {
        try {
            return trainingDefRepository.getAvailable(trainingDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get active training definitions");
        }
    }

    public void share(Long partyId, Long trainingDefId) throws Exception {
        try {
            trainingDefRepository.share(partyId, trainingDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to share training definition " + trainingDefId + " to party " + partyId);
        }
    }

    public void addTraining(Long trainingId, Long exerciseId) throws Exception {
        try {
            trainingDefRepository.addExerciseDef(trainingId, exerciseId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to add exercise " + exerciseId + " to training " + trainingId);
        }
    }

    public void removeTraining(Long trainingId, Long exerciseId) throws Exception {
        try {
            trainingDefRepository.removeExerciseDef(trainingId, exerciseId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to remove exercise " + exerciseId + " from training " + trainingId);
        }
    }
}
