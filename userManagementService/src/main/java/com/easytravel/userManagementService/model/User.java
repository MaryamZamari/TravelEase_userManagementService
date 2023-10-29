package com.easytravel.userManagementService.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name= "users")
@EntityListeners(UserEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String userName;
    private String password;
    @Column(unique=true)
    private String email;
    private LocalDate birthday;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;



    public User(Long id, String userName, String password, String email, LocalDate birthday, Date registrationDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday= birthday;
        this.registrationDate= new Date();
    }
    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
