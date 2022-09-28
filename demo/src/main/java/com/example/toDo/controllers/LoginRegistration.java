package com.example.toDo.controllers;

import com.example.toDo.models.Roles;
import com.example.toDo.models.Users;
import com.example.toDo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.stream.Stream;

@Controller
public class LoginRegistration {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/home/sign")
    public String sign(Model model) {
        return "sign";
    }

    @GetMapping("/home/registration")
    public String registration(Model model) {
        return "registration";

    }

    @PostMapping("/home/registration")
    public String userRegistration(Users users, Model model) {
        Users usersFromDb = usersRepository.findByLogin(users.getLogin());
        if(usersFromDb !=null){
            model.addAttribute("userExist","User Exist");
            return "registration";
        }
        users.setRoles(Collections.singleton(Roles.User));
        usersRepository.save(users);
    return "/home/sign";
    }

}


