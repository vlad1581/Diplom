package com.example.toDo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("greeting","Glad to see you at our " +
                "service for making toDoList");
        return "home";
    }

    @GetMapping("/feature")
    public String feature(Model model){
        model.addAttribute("feature","Now let's doing your toDo");
        return "feature";
    }

    @GetMapping("/pricing")
    public String pricing(Model model){
        model.addAttribute("pricing","Unfortunately, it is not free");
        return "pricing";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("about","Now let's doing your toDo");
        return "about";
    }

    @GetMapping("/faqs")
    public String faqs(Model model){
        model.addAttribute("faqs","Now let's doing your toDo");
        return "faqs";
    }
}
