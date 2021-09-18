package com.rps.projekat.service;

import com.rps.projekat.dto.RoutineDefDTO;
import com.rps.projekat.dto.TrainingDefDTO;
import com.rps.projekat.entity.RoutineDef;
import com.rps.projekat.repository.RoutineDefRepository;
import com.rps.projekat.repository.TrainingDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoutineDefService {

    @Autowired
    RoutineDefRepository routineDefRepository;

    @Autowired
    TrainingDefRepository trainingDefRepository;

    public void insert(RoutineDefDTO routineDefDTO) throws Exception {
        try {
            routineDefRepository.insert(routineDefDTO.getName(), routineDefDTO.getDescription(),
                    routineDefDTO.getCreatedBy(), routineDefDTO.getPartyId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to insert routine definition");
        }
    }

    public int update(RoutineDef routineDef) throws Exception {
        try {
            return routineDefRepository.update(routineDef.getName(), routineDef.getDescription(), routineDef.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to update routine definition " + routineDef.getId());
        }
    }

    public int publish(Long routineDefId) throws Exception {
        try {
            return routineDefRepository.publish(routineDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to publish routine definition " + routineDefId);
        }
    }

    public int delete(Long routineDefId) throws Exception {
        try {
            return routineDefRepository.delete(routineDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to delete routine definition " + routineDefId);
        }
    }

    public List<RoutineDefDTO> getAll(Long partyId) throws Exception {
        try {
            List<RoutineDefDTO> result = new ArrayList<>();
            List<RoutineDef> routineDefs = routineDefRepository.getAll(partyId);
            for (RoutineDef routineDef: routineDefs) {
                RoutineDefDTO routineDefDTO = new RoutineDefDTO(routineDef);
                List<TrainingDefDTO> trainingDefs = trainingDefRepository.getAllForRoutine(routineDef.getId());
                routineDefDTO.setTrainingDefs(trainingDefs);
                result.add(routineDefDTO);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get routine definitions");
        }
    }

    public List<RoutineDef> getAvailable(Long partyId) throws Exception {
        try {
            return routineDefRepository.getAvailable(partyId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get available routine definitions for party " + partyId);
        }
    }

    public void share(Long partyId, Long routineDefId) throws Exception {
        try {
            routineDefRepository.share(partyId, routineDefId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to share routine definition " + routineDefId + " to party " + partyId);
        }
    }

    public void addTraining(Long routineId, Long trainingId, int dayOfWeek) throws Exception {
        try {
            routineDefRepository.addTrainingDef(routineId, trainingId, dayOfWeek);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to add training " + trainingId + " to routine " + routineId);
        }
    }

    public void removeTraining(Long routineId, Long trainingId, int dayOfWeek) throws Exception {
        try {
            routineDefRepository.removeTrainingDef(routineId, trainingId, dayOfWeek);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to remove training " + trainingId + " from routine " + routineId);
        }
    }


}
