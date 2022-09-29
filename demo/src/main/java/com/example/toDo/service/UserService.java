package com.example.toDo.service;

import com.example.toDo.models.Users;
import com.example.toDo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;
    @Override
    public Users loadUserByUsername(String login) throws UsernameNotFoundException {
        return usersRepository.findByLogin(login);
    }

     public Users findByLoginAndPassword(String login,String password) throws UsernameNotFoundException {
        Users users = loadUserByUsername(login);
        if(users != null && passwordEncoder.matches(password, users.getPassword())){
        return users;
        }
        return null;
     }


}
