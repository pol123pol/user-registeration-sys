package com.accn.controller;

import com.accn.business.bean.User;
import com.accn.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service/user-mgmnt")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        logger.info("Add User accessed***********");
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("User form has following error :" + bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }
        else{
            long userId = userService.addUser(user);
            return new ResponseEntity<String>("User added successfully with id:" + userId, HttpStatus.CREATED);
        }

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserDetailsbyId(@PathVariable("userId") int userId) {
        logger.info("Get User by Id accessed***********");
        User user = userService.getUserDetailsbyId(userId);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{userId}")
    private ResponseEntity<User> deleteProduct(@PathVariable("userId") int userId) {
        logger.info("Delete User accessed***********");
        User user = userService.deleteUser(userId);
        if (user == null) {
            return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        logger.info("Update User accessed***********");
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Get all Users accessed***********");
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

}
