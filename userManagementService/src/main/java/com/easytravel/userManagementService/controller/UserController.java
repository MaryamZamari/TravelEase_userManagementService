package com.easytravel.userManagementService.controller;

import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/users")
public class UserController {

    @Autowired
    UserService service;


   @GetMapping("/{id}") //TODO: Each user should be able to see only their own details. So if i logged in with {id}, the url should be {id}. Only if the request is comming from a User with ADMIN role, it can check other people's details.
   public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> user = service.findUser(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
     return  ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        service.updatePassword(id, user);
    }






}
