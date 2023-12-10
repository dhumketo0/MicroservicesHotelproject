package com.lcwd.userService.controllers;

import com.lcwd.userService.Services.UserService;
import com.lcwd.userService.Services.impl.UserServiceImpl;
import com.lcwd.userService.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
    User user1=userService.saveUser(user) ;
        System.out.println("Hi baddie");
    return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("getting single user handler: USER-CONTROLLER");
        User user=userService.getUSer(userId);
        return ResponseEntity.ok(user);
    }
//creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down"+ex.getMessage());
        User user=User.builder()
                .email("dummyemaoil@gmail.com")
                .name("dummy")
                .about("This is a dummy user created since the server is down")
                .user_id("1234567")
                .build();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
