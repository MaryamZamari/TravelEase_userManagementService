package com.easytravel.userManagementService.controller;

import com.easytravel.userManagementService.model.RegistrationRequest;
import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
public class PublicController {

    @Autowired
    UserService service;
    private final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody RegistrationRequest user){
        logger.info("Received a request to create a user: {}", user);
        service.registerUser(user);
        return ResponseEntity.ok("User created successfully");
    }
}
