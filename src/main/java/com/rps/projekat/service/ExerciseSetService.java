package com.rps.projekat.service;

import com.rps.projekat.entity.ExerciseSet;
import com.rps.projekat.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseSetService {

    @Autowired
    ExerciseSetRepository exerciseSetRepository;

    public void insert(ExerciseSet exerciseSet) throws Exception {
        try {
            exerciseSetRepository.insert(exerciseSet.getExerciseId(), exerciseSet.getNumberOfReps(),
                    exerciseSet.getDistanceValue(), exerciseSet.getWeightValue(), exerciseSet.getTimeValue());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to create exercise set");
        }
    }

    public int delete(Long exerciseSetId) throws Exception {
        try {
            return exerciseSetRepository.delete(exerciseSetId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to delete exercise set " + exerciseSetId);
        }
    }
}
