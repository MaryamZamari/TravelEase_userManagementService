package com.easytravel.userManagementService.model;

import jakarta.persistence.PrePersist;

import java.util.Date;

public class UserEntityListener {//acts as a trigger in db so i can set rules
    @PrePersist
    public void onPrePersist(User user){
        user.setRegistrationDate(new Date());
        if(user.getRole()== null){
            user.setRole(Role.AUTH_USER);
        };
    }

    //TODO: create a method EXCEPTION handling for password strength
    // -------> set rules for password strength : up and lowercase, length and special character and number.
    // it should be implemented both by createUser and UpdateUser
    public void passwordStrengthControl(String password){

    }


}
