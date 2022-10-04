package com.example.toDo.controllers;

import com.example.toDo.models.User;
import com.example.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/home/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";

    }

    @PostMapping("/home/registration")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Password isn't confirm");
        }
        if (!userService.userRegistration(user)){
            model.addAttribute("userNameError","This user has been exist");
        }
    return "redirect:/home/sign";
    }
}


