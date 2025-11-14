package com.Devansh.Journal.admin;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Service.User_Service;
import com.Devansh.Journal.appcache.appCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private User_Service userService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Autowired
    private appCache appCache;



    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody  User user){
        userService.saveAdmin(user);
    }


    @GetMapping("/clear_cache")
    public void clearCache(){
        appCache.init();
    }


}
