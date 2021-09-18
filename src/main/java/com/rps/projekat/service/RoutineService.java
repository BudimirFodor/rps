package com.rps.projekat.service;

import com.rps.projekat.dto.RoutineDTO;
import com.rps.projekat.dto.RoutineDefDTO;
import com.rps.projekat.dto.TrainingDefDTO;
import com.rps.projekat.repository.RoutineRepository;
import com.rps.projekat.repository.TrainingDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineService {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    TrainingDefRepository trainingDefRepository;

    public List<RoutineDTO> getAll(Long partyId) throws Exception {
        try {
            List<RoutineDTO> result = routineRepository.getAll(partyId);
            for (RoutineDTO routineDTO: result) {
                List<TrainingDefDTO> trainingDefs = trainingDefRepository.getAllForRoutine(routineDTO.getRoutineDefId());
                routineDTO.setTrainingDefs(trainingDefs);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get routines");
        }
    }

    public int subscribe(RoutineDefDTO routineDefDTO) throws Exception {
        try {
            return routineRepository.subscribe(routineDefDTO.getId(), routineDefDTO.getPartyId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to subscribe to routine " + routineDefDTO.getId());
        }
    }

}
