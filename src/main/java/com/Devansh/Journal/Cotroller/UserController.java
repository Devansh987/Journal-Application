package com.Devansh.Journal.Cotroller;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Repo.UserRepo;
import com.Devansh.Journal.Service.Journal_entry_service;
import com.Devansh.Journal.Service.User_Service;
import com.Devansh.Journal.Service.WeatherService;
import com.Devansh.Journal.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private Journal_entry_service journalEntryService;
    @Autowired
    private User_Service userEntryService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private UserRepo userRepo;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userId=userEntryService.findByUserName(userName);
        userId.setUserName(user.getUserName());
        userId.setPassword(user.getPassword());
        userEntryService.saveNewUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherService.getWeather("Mumbai");
        String greetings ="";
        if(response!=null){
            greetings = "weather feels like "+ response.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName()+greetings,HttpStatus.OK);
    }
}
