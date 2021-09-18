package com.rps.projekat.service;

import com.rps.projekat.dto.ExerciseDTO;
import com.rps.projekat.entity.ExerciseSet;
import com.rps.projekat.repository.ExerciseRepository;
import com.rps.projekat.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseSetRepository exerciseSetRepository;

    public List<ExerciseDTO> getForTraining(Long trainingId) throws Exception {
        try {
            List<ExerciseDTO> result = exerciseRepository.getForTraining(trainingId);
            for(ExerciseDTO exerciseDTO: result) {
                List<ExerciseSet> exerciseSets = exerciseSetRepository.getAll(exerciseDTO.getId());
                exerciseDTO.setExerciseSets(exerciseSets);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to fetch exercises for training " + trainingId);
        }
    }
}
