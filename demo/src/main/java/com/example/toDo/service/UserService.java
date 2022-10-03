package com.example.toDo.service;

import com.example.toDo.models.Roles;
import com.example.toDo.models.User;
import com.example.toDo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return usersRepository.findByLogin(login);
    }

    public boolean userRegistration(User user) {
        User userFromDb = usersRepository.findByLogin(user.getLogin());
        if (userFromDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(Roles.User));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }
}

     /*public User findByLoginAndPassword(String login,String password) throws UsernameNotFoundException {
        User users = loadUserByUsername(login);
        if(users != null && passwordEncoder.matches(password, users.getPassword())){
        return users;
        }
        return null;
     }*/
