package com.Devansh.Journal.Cotroller;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class Public {
    @Autowired
    private User_Service userService;

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

}
