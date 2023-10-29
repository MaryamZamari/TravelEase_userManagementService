package com.easytravel.userManagementService.repository;

import com.easytravel.userManagementService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
public User findByUserName(String userName);
public User findByEmail(String email);

public User findByBirthday(LocalDate birthday);
}
