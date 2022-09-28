package com.example.toDo.controllers;

import com.example.toDo.models.ToDoLists;
import com.example.toDo.models.Users;
import com.example.toDo.repo.ToDoListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class ToDoListsPage {
    @Autowired
    private ToDoListsRepository toDoListsRepository;

    @GetMapping("/toDoListsPage")
    private String toDoListsPage(Model model) {
        Iterable<ToDoLists> toDoLists = toDoListsRepository.findAll();
        model.addAttribute("toDoLists", toDoLists);
        return "toDoListsPage";
    }

    @GetMapping("/addToDoLists")
    private String addToDo(Model model) {
        LocalDateTime date = LocalDateTime.from(LocalDateTime.now());
        model.addAttribute("date",date);
        return "addToDoLists";
    }

    @PostMapping("/addToDoLists")
    private String addToDo( @RequestParam String planningDate,@RequestParam String date,
                           @RequestParam String title, @RequestParam String notes, Model model) {
        ToDoLists toDoLists = new ToDoLists(planningDate,date,title, notes);
        toDoListsRepository.save(toDoLists);
        return "redirect:/toDoListsPage";
    }

    @GetMapping("/toDoListsPage/{toDoListsId}")
    private String toDoListsPageDetails(@PathVariable(value = "toDoListsId") long toDoListsId, Model model) {
        if(!toDoListsRepository.existsById(toDoListsId)){
            return "toDoListsPage";
        }
        Optional<ToDoLists> toDo = toDoListsRepository.findById(toDoListsId);
        ArrayList<ToDoLists> lists = new ArrayList<>();
        toDo.ifPresent(lists::add);
        model.addAttribute("toDo", lists);
        return "MoreDetailsToDoListsPage";
    }

    @GetMapping("/toDoListsPage/{toDoListsId}/editToDoLists")
    private String editToDo(@PathVariable(value = "toDoListsId") long toDoListsId, Model model) {
        if(!toDoListsRepository.existsById(toDoListsId)){
            return "redirect:/toDoListsPage";
        }
        LocalDateTime date = LocalDateTime.from(LocalDateTime.now());
        model.addAttribute("date",date);
        Optional<ToDoLists> toDo = toDoListsRepository.findById(toDoListsId);
        ArrayList<ToDoLists> lists = new ArrayList<>();
        toDo.ifPresent(lists::add);
        model.addAttribute("toDo", lists);
        return "editToDoLists";
    }

    @PostMapping("/toDoListsPage/{toDoListsId}/editToDoLists")
    private String updateToDo(@PathVariable(value = "toDoListsId") long toDoListsId, @RequestParam String planningDate,@RequestParam String date,
                            @RequestParam String title, @RequestParam String notes, Model model) {
        ToDoLists toDoLists = toDoListsRepository.findById(toDoListsId).orElseThrow();
        toDoLists.setPlanningDate(planningDate);
        toDoLists.setDate(date);
        toDoLists.setTitle(title);
        toDoLists.setNotes(notes);
        toDoListsRepository.save(toDoLists);
        return "redirect:/toDoListsPage";
    }
    @PostMapping("/toDoListsPage/{toDoListsId}/removeToDoLists")
    private String removeToDo(@PathVariable(value = "toDoListsId") long toDoListsId, Model model) {
        ToDoLists toDoLists = toDoListsRepository.findById(toDoListsId).orElseThrow();
        toDoListsRepository.delete(toDoLists);
        return "redirect:/toDoListsPage";
}
}
