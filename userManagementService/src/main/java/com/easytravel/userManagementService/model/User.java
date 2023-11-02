package com.easytravel.userManagementService.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "users")
@EntityListeners(UserEntityListener.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String userName;
    private String password;
    @Column(unique=true)
    private String email;
    private LocalDate birthday;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role;



    public User(Long id, String userName, String password, String email, LocalDate birthday, Date registrationDate, Role role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday= birthday;
        this.registrationDate= new Date();
        this.role= role;
    }
    public User() {
        super();
    }
    public User(String firstName, String lastName, String userName, String encode, LocalDate birthday) {
    }
    public User(String username, String password, List<GrantedAuthority> authorityList) {
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getlastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //TODO: Add a lastUpdated field with Date format (datetime), that would be set, each time the user modifies the password.

    //-----------UserDetails Interface implementations for spring security (credential based authentication)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return this.userName;
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
    public Enum<Role> getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() { //for failed logins. for now i just set true because i dont want it to ever lock
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() { //can check later on if to put some logic for it or not. for now i want it to be enabled anyway.
        return true;
    }
}
