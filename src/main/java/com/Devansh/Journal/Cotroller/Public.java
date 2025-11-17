package com.Devansh.Journal.Cotroller;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Service.UserDetailServiceImpl;
import com.Devansh.Journal.Service.User_Service;
import com.Devansh.Journal.util.Jwtutil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public")
public class Public {
    @Autowired
    private User_Service userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private Jwtutil jwtutil;

    @PostMapping("/sign-up")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String token = jwtutil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token,HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception while creating authentication token ",e);

        }
        return new ResponseEntity<>("Incorrect UserName and Password",HttpStatus.NOT_FOUND);
    }



}
