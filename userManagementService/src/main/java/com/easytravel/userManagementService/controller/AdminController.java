package com.easytravel.userManagementService.controller;

import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminController {

    @Autowired
    UserService service;
    public static final Logger logger= LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info("AdminController: Received a request to get all users!");
        List<User> users= service.findUsers();
        return  ResponseEntity.ok(users);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

}
