package com.example.toDo.controllers;

import com.example.toDo.models.User;
import com.example.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DeleteController {
    @Autowired
    private UserService userService;

    @PostMapping("/toDoListsPage")
    public String deleteUser(@AuthenticationPrincipal User user){
        userService.userDelete(user);
        return "redirect:/home";
    }
}
