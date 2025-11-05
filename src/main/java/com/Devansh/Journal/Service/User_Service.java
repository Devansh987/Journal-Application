package com.Devansh.Journal.Service;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Repo.JournalRepo;
import com.Devansh.Journal.Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class User_Service {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JournalRepo journalRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public void saveNewUser(User user){
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userRepo.save(user);
    }catch (Exception e){
            log.info("hahaha");
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","ADMIN"));
        userRepo.save(user);
    }


    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }


    public void deleteByUserName(String UserName){
        User user = userRepo.findByUserName(UserName);
        userRepo.delete(user);
    }

    public Optional<User> getById(ObjectId id){
        return userRepo.findById(id);
    }








}
