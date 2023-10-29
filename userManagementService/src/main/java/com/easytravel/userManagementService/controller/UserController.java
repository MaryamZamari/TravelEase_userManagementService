package com.easytravel.userManagementService.controller;

import com.easytravel.userManagementService.model.User;
import com.easytravel.userManagementService.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users= service.findUsers();
        return  ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
   public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> user = service.findUser(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
     return  ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }





}
