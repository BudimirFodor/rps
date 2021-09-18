package com.rps.projekat.controller;

import com.rps.projekat.ResponseBody;
import com.rps.projekat.dto.ExerciseDTO;
import com.rps.projekat.dto.ExerciseDefDTO;
import com.rps.projekat.entity.ExerciseDef;
import com.rps.projekat.entity.ExerciseSet;
import com.rps.projekat.service.ExerciseDefService;
import com.rps.projekat.service.ExerciseService;
import com.rps.projekat.service.ExerciseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins = "http://localhost:8080")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseDefService exerciseDefService;

    @Autowired
    ExerciseSetService exerciseSetService;

    @GetMapping("/{trainingId}")
    public ResponseEntity getAllForTraining(@PathVariable Long trainingId) {
        try {
            List<ExerciseDTO> result = exerciseService.getForTraining(trainingId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition")
    public ResponseEntity insertExerciseDef(@RequestBody ExerciseDefDTO exerciseDefDTO) {
        try {
            exerciseDefService.insert(exerciseDefDTO);
            return ResponseEntity.ok(new ResponseBody("Exercise definition successfully created"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition")
    public ResponseEntity updateExerciseDef(@RequestBody ExerciseDef exerciseDef) {
        try {
            int updated = exerciseDefService.update(exerciseDef);
            if (updated != 0) {
                return ResponseEntity.ok(new ResponseBody("Exercise definition successfully updated"));
            } else {
                throw new Exception("Unable to update exercise definition " + exerciseDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition/publish")
    public ResponseEntity publishExerciseDef(@RequestBody ExerciseDef exerciseDef) {
        try {
            int published = exerciseDefService.publish(exerciseDef.getId());
            if (published != 0) {
                return ResponseEntity.ok(new ResponseBody("Exercise definition successfully published"));
            } else {
                throw new Exception("Unable to publish exercise definition " + exerciseDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/definition/{id}")
    public ResponseEntity deleteExerciseDef(@PathVariable Long id) {
        try {
            int deleted = exerciseDefService.delete(id);
            if (deleted != 0) {
                return ResponseEntity.ok(new ResponseBody("Exercise definition successfully deleted"));
            } else {
                throw new Exception("Unable to delete exercise definition " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/{partyId}")
    public ResponseEntity getExerciseDefs(@PathVariable Long partyId) {
        try {
            List<ExerciseDef> result = exerciseDefService.getAll(partyId);
                return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/available/{trainingDefId}")
    public ResponseEntity getAvailableExerciseDefs(@PathVariable Long trainingDefId) {
        try {
            List<ExerciseDef> result = exerciseDefService.getAvailable(trainingDefId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition/share/{partyId}")
    public ResponseEntity shareExerciseDef(@RequestBody ExerciseDef exerciseDef, @PathVariable Long partyId) {
        try {
            exerciseDefService.share(partyId, exerciseDef.getId());
            return ResponseEntity.ok(new ResponseBody("Exercise definition shared successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/set")
    public ResponseEntity insertExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        try {
            exerciseSetService.insert(exerciseSet);
            return ResponseEntity.ok(new ResponseBody("Exercise set created successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("set/{id}")
    public ResponseEntity deleteExerciseSet(@PathVariable Long id) {
        try {
            int deleted = exerciseSetService.delete(id);
            if (deleted != 0) {
                return ResponseEntity.ok(new ResponseBody("Exercise definition successfully deleted"));
            } else {
                throw new Exception("Unable to delete exercise definition " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }
}
