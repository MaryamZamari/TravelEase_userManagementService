package com.easytravel.userManagementService.model;

import jakarta.persistence.PrePersist;

import java.util.Date;

public class UserEntityListener {//acts as a trigger in db so i can set rules
    @PrePersist
    public void onPrePersist(User user){
        user.setRegistrationDate(new Date());
    }
}
