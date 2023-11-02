package com.easytravel.userManagementService.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
//@Getter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String userName;
    private String password;
    @Column(unique=true)
    private String email;
    private LocalDate birthday;

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
