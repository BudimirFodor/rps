package com.rps.projekat.controller;

import com.rps.projekat.ResponseBody;
import com.rps.projekat.dto.PartyDTO;
import com.rps.projekat.entity.Party;
import com.rps.projekat.service.PartyService;
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
@RequestMapping("/party")
@CrossOrigin(origins = "http://localhost:8080")
public class PartyController {

    @Autowired
    PartyService partyService;

    @PostMapping
    public ResponseEntity insertParty(@RequestBody Party party) {
        try {
            partyService.insertParty(party);
            return ResponseEntity.ok(new ResponseBody("Party successfully created"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getParty(@PathVariable Long id) {
        try {
            Party party = partyService.getParty(id);
            return ResponseEntity.ok(party);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @GetMapping("/{id}/all")
    public ResponseEntity getOtherParties(@PathVariable Long id) {
        try {
            List<Party> parties = partyService.getOtherParties(id);
            return ResponseEntity.ok(parties);
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParty(@PathVariable Long id) {
        try {
            int deleted = partyService.deleteParty(id);
            if (deleted != 0) {
                return ResponseEntity.ok(new ResponseBody("Party successfully deleted"));
            } else {
                throw new Exception("Unable to delete party " + id);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity updateParty(@RequestBody Party party) {
        try {
            int updated = partyService.updateParty(party);
            if (updated != 0) {
                return ResponseEntity.ok(new ResponseBody("Party successfully updated"));
            } else {
                throw new Exception("Unable to update party " + party.getId());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }

    @PutMapping("/password")
    public ResponseEntity updatePartyPassword(@RequestBody PartyDTO partyDTO) {
        try {
            int updated = partyService.updatePartyPassword(partyDTO);
            if (updated != 0) {
                return ResponseEntity.ok(new ResponseBody("Party password successfully updated"));
            } else {
                throw new Exception("Unable to update party " + partyDTO.getId() + " password");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ResponseBody(ex.getMessage()));
        }
    }
}
