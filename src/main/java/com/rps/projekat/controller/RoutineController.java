package com.rps.projekat.controller;

import com.rps.projekat.ResponseBody;
import com.rps.projekat.dto.AddChildDTO;
import com.rps.projekat.dto.RoutineDTO;
import com.rps.projekat.dto.RoutineDefDTO;
import com.rps.projekat.entity.RoutineDef;
import com.rps.projekat.service.RoutineDefService;
import com.rps.projekat.service.RoutineService;
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
@RequestMapping("/routine")
@CrossOrigin(origins = "http://localhost:8080")
public class RoutineController {

    @Autowired
    RoutineService routineService;

    @Autowired
    RoutineDefService routineDefService;

    @PostMapping("/subscribe")
    public ResponseEntity subscribeToRoutine(@RequestBody RoutineDefDTO routineDefDTO) {
        try {
            routineService.subscribe(routineDefDTO);
            return ResponseEntity.ok(new ResponseBody("Successfully subscribe to routine " + routineDefDTO.getId()));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/all/{partyId}")
    public ResponseEntity getRoutines(@PathVariable Long partyId) {
        try {
            List<RoutineDTO> result = routineService.getAll(partyId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition")
    public ResponseEntity insertRoutineDef(@RequestBody RoutineDefDTO routineDefDTO) {
        try {
            routineDefService.insert(routineDefDTO);
            return ResponseEntity.ok(new ResponseBody("Successfully inserted routine definition"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition")
    public ResponseEntity updateRoutineDef(@RequestBody RoutineDef routineDef) {
        try {
            int updated = routineDefService.update(routineDef);
            if (updated != 0) {
                return ResponseEntity.ok(new ResponseBody("Routine definition successfully updated"));
            } else {
                throw new Exception("Unable to update routine definition " + routineDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/definition/publish")
    public ResponseEntity publishRoutineDef(@RequestBody RoutineDef routineDef) {
        try {
            int published = routineDefService.publish(routineDef.getId());
            if (published != 0) {
                return ResponseEntity.ok(new ResponseBody("Routine definition successfully published"));
            } else {
                throw new Exception("Unable to publish routine definition " + routineDef.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/definition/{id}")
    public ResponseEntity deleteRoutineDef(@PathVariable Long id) {
        try {
            int deleted = routineDefService.delete(id);
            if (deleted != 0) {
                return ResponseEntity.ok(new ResponseBody("Routine definition successfully deleted"));
            } else {
                throw new Exception("Unable to delete routine definition " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/{partyId}")
    public ResponseEntity getRoutineDefs(@PathVariable Long partyId) {
        try {
            List<RoutineDefDTO> result = routineDefService.getAll(partyId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/definition/available/{partyId}")
    public ResponseEntity getAvailableRoutineDefs(@PathVariable Long partyId) {
        try {
            List<RoutineDef> result = routineDefService.getAvailable(partyId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition/share/{partyId}")
    public ResponseEntity shareRoutineDef(@RequestBody RoutineDef routineDef, @PathVariable Long partyId) {
        try {
            routineDefService.share(partyId, routineDef.getId());
            return ResponseEntity.ok(new ResponseBody("Routine definition shared successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PostMapping("/definition/training")
    public ResponseEntity addTrainingDef(@RequestBody AddChildDTO addChildDTO) {
        try {
            routineDefService.addTraining(addChildDTO.getParentId(), addChildDTO.getChildId(), addChildDTO.getDayOfWeek());
            return ResponseEntity.ok(new ResponseBody("Training definition added successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/definition/training")
    public ResponseEntity removeTrainingDef(@RequestParam Long routineId, @RequestParam Long trainingId, @RequestParam int dayOfWeek) {
        try {
            routineDefService.removeTraining(routineId, trainingId, dayOfWeek);
            return ResponseEntity.ok(new ResponseBody("Training definition removed successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

}
