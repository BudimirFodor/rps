package com.rps.projekat.controller;

import com.rps.projekat.ResponseBody;
import com.rps.projekat.dto.AddChildDTO;
import com.rps.projekat.dto.CreateTrainingDTO;
import com.rps.projekat.dto.TrainingDTO;
import com.rps.projekat.dto.TrainingDefDTO;
import com.rps.projekat.entity.Training;
import com.rps.projekat.entity.TrainingDef;
import com.rps.projekat.service.TrainingDefService;
import com.rps.projekat.service.TrainingService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/training")
@CrossOrigin(origins = "http://localhost:8080")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @Autowired
    TrainingDefService trainingDefService;

    @PostMapping
    public ResponseEntity insertTraining(@RequestBody CreateTrainingDTO createTrainingDTO) {
        try {
            trainingService.insert(createTrainingDTO);
            return ResponseEntity.ok(new ResponseBody("Successfully created a new training"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/complete")
    public ResponseEntity completeTraining(@RequestBody Training training) {
        try {
            trainingService.complete(training.getId());
            return ResponseEntity.ok(new ResponseBody("Successfully completed training " + training.getId()));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/all/{routineId}")
    public ResponseEntity getTrainings(@PathVariable Long routineId, @RequestParam String dateFrom, @RequestParam String dateTo) {
        try {
            List<TrainingDTO> result = trainingService.getForRoutineMonth(routineId, dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition")
    public ResponseEntity insertTrainingDef(@RequestBody TrainingDefDTO trainingDefDTO) {
        try {
            trainingDefService.insert(trainingDefDTO);
            return ResponseEntity.ok(new ResponseBody("Successfully inserted training definition"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition")
    public ResponseEntity updateTrainingDef(@RequestBody TrainingDef trainingDef) {
        try {
            int updated = trainingDefService.update(trainingDef);
            if (updated != 0) {
                return ResponseEntity.ok(new ResponseBody("Training definition successfully updated"));
            } else {
                throw new Exception("Unable to update training definition " + trainingDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition/publish")
    public ResponseEntity publishTrainingDef(@RequestBody TrainingDef trainingDef) {
        try {
            int published = trainingDefService.publish(trainingDef.getId());
            if (published != 0) {
                return ResponseEntity.ok(new ResponseBody("Training definition successfully published"));
            } else {
                throw new Exception("Unable to publish training definition " + trainingDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/definition/{id}")
    public ResponseEntity deleteTrainingDef(@PathVariable Long id) {
        try {
            int deleted = trainingDefService.delete(id);
            if (deleted != 0) {
                return ResponseEntity.ok(new ResponseBody("Training definition successfully deleted"));
            } else {
                throw new Exception("Unable to delete training definition " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/{partyId}")
    public ResponseEntity getTrainingDefs(@PathVariable Long partyId) {
        try {
            List<TrainingDefDTO> result = trainingDefService.getAll(partyId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition/share/{partyId}")
    public ResponseEntity shareTrainingDef(@RequestBody TrainingDef trainingDef, @PathVariable Long partyId) {
        try {
            trainingDefService.share(partyId, trainingDef.getId());
            return ResponseEntity.ok(new ResponseBody("Training definition shared successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/available/{routineDefId}")
    public ResponseEntity getAvailableTrainingDefs(@PathVariable Long routineDefId) {
        try {
            List<TrainingDef> result = trainingDefService.getAvailable(routineDefId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition/exercise")
    public ResponseEntity addExerciseDef(@RequestBody AddChildDTO addChildDTO) {
        try {
            trainingDefService.addTraining(addChildDTO.getParentId(), addChildDTO.getChildId());
            return ResponseEntity.ok(new ResponseBody("Exercise definition added successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/definition/exercise")
    public ResponseEntity removeExerciseDef(@RequestParam Long trainingId, @RequestParam Long exerciseId) {
        try {
            trainingDefService.removeTraining(trainingId, exerciseId);
            return ResponseEntity.ok(new ResponseBody("Exercise definition removed successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

}
