package com.Devansh.Journal.Service;

import com.Devansh.Journal.Entity.User;
import com.Devansh.Journal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user!=null){
            return org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword())
                     .roles(user.getRoles().toArray(new String[0] )).build();
        }
        throw new UsernameNotFoundException("UserName not Found");
    }
}
