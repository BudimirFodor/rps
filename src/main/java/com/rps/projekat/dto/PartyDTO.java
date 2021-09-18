package com.rps.projekat.dto;

import com.rps.projekat.entity.Party;

public class PartyDTO extends Party {

    private String oldPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
