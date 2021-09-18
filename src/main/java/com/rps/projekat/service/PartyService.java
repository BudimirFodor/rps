package com.rps.projekat.service;

import com.rps.projekat.EntityStatus;
import com.rps.projekat.dto.PartyDTO;
import com.rps.projekat.entity.Party;
import com.rps.projekat.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PartyService {

    @Autowired
    PartyRepository partyRepository;

    public void insertParty(Party party) throws Exception {
        try {
            party.setStatus(EntityStatus.ACTIVE);
            partyRepository.save(party);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to create party");
        }
    }

    public Party getParty(Long partyId) throws Exception {
        try {
            return partyRepository.findById(partyId).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to fetch party " + partyId);
        }
    }

    public List<Party> getOtherParties(Long partyId) throws Exception {
        try {
            return partyRepository.getOtherParties(partyId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to get other parties");
        }
    }

    @Transactional
    public int deleteParty(Long partyId) {
        return partyRepository.delete(partyId);
    }

    @Transactional
    public int updateParty(Party party) throws Exception {
        try {
            return partyRepository.update(party.getFirstName(), party.getLastName(), party.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to update party " + party.getId());
        }
    }

    @Transactional
    public int updatePartyPassword(PartyDTO partyDTO) throws Exception {
        try {
            Party foundParty = partyRepository.confirmPassword(partyDTO.getId(), partyDTO.getOldPassword());
            if (foundParty == null) {
                throw new Exception("Wrong password");
            }
            return partyRepository.changePassword(partyDTO.getPassword(), partyDTO.getId());
        } catch (Exception ex) {
            Object x = ex.getCause();
            if (ex.getMessage().equals("Wrong password")) {
                throw ex;
            }
            throw new Exception("Unable to update party " + partyDTO.getId() + " password");
        }

    }
}
