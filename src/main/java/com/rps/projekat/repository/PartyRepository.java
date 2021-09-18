package com.rps.projekat.repository;

import com.rps.projekat.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query(value = "SELECT * FROM party WHERE id != ?", nativeQuery = true)
    List<Party> getOtherParties(Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE party SET status = 103 WHERE id = ?", nativeQuery = true)
    int delete(Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE party SET first_name = ?, last_name = ? WHERE id = ?", nativeQuery = true)
    int update(String firstName, String lastName, Long partyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE party SET password = ? WHERE id = ?", nativeQuery = true)
    int changePassword(String password, Long partyId);

    @Query(value = "SELECT * FROM party WHERE id = ? AND password = ?", nativeQuery = true)
    Party confirmPassword(Long partyId, String password);
}
