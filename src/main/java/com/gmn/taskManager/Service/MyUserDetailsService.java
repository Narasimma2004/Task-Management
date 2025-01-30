package com.gmn.taskManager.Service;


import com.gmn.taskManager.Entity.UserPrinciples;
import com.gmn.taskManager.Entity.Users;
import com.gmn.taskManager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersRepository.findByName(username);
        if(users==null)
        {
            throw new UsernameNotFoundException("user not found "+username);
        }
        return new User(users.getName(),users.getPassword(),new ArrayList<>());
    }
}
