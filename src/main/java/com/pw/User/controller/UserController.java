package com.pw.User.controller;

import com.pw.User.entity.User;
import com.pw.User.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImp serviceImp;

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        serviceImp.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable int id) {
        User users = serviceImp.getUserById(id);
        if(users == null){
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        serviceImp.upadteUser(users, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = serviceImp.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = serviceImp.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User user = serviceImp.getUserById(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serviceImp.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
