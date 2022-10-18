package com.example.toDo.controllers;

import com.example.toDo.models.ToDoList;
import com.example.toDo.models.User;
import com.example.toDo.repo.ToDoListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.rowset.spi.SyncResolver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ToDoListsPageController {
    @Autowired
    private ToDoListsRepository toDoListsRepository;

    @GetMapping("/toDoListsPage")
    private String toDoListsPage( @AuthenticationPrincipal User user, Model model) {

       ToDoList toDoLists = toDoListsRepository.findByUser(user);
        model.addAttribute("toDoLists", toDoLists);
        return "toDoListsPage";
    }

    @GetMapping("/addToDoLists")
    private String addToDo(@AuthenticationPrincipal ToDoList user, Model model) {
        LocalDateTime date = LocalDateTime.from(LocalDateTime.now());
        model.addAttribute("date",date);
        return "addToDoLists";
    }

    @PostMapping("/addToDoLists")
    private String addToDo(@AuthenticationPrincipal User user, @RequestParam String planningDate, @RequestParam String date,
                           @RequestParam String title, @RequestParam String notes, Model model) {
        ToDoList toDoList = new ToDoList(planningDate,date,title, notes, user);
        toDoListsRepository.save(toDoList);
        return "redirect:/toDoListsPage";
    }

    @GetMapping("/toDoListsPage/{toDoListsId}")
    private String toDoListsPageDetails(@PathVariable(value = "toDoListsId") long toDoListsId, @AuthenticationPrincipal User user, Model model) {
        if(!toDoListsRepository.existsById(toDoListsId)){
            return "toDoListsPage";
        }
        Optional<ToDoList> toDo = toDoListsRepository.findById(toDoListsId);
        ArrayList<ToDoList> lists = new ArrayList<>();
        toDo.ifPresent(lists::add);
        model.addAttribute("toDo", lists);
        return "MoreDetailsToDoListsPage";
    }

    @GetMapping("/toDoListsPage/{toDoListsId}/editToDoLists")
    private String editToDo(@PathVariable(value = "toDoListsId") long toDoListsId, @AuthenticationPrincipal User user, Model model) {
        if(!toDoListsRepository.existsById(toDoListsId)){
            return "redirect:/toDoListsPage";
        }
        LocalDateTime date = LocalDateTime.from(LocalDateTime.now());
        model.addAttribute("date",date);
        Optional<ToDoList> toDo = toDoListsRepository.findById(toDoListsId);
        ArrayList<ToDoList> lists = new ArrayList<>();
        toDo.ifPresent(lists::add);
        model.addAttribute("toDo", lists);
        return "editToDoLists";
    }

    @PostMapping("/toDoListsPage/{toDoListsId}/editToDoLists")
    private String updateToDo(@PathVariable(value = "toDoListsId") long toDoListsId, @RequestParam String planningDate,@RequestParam String date,
                            @RequestParam String title, @RequestParam String notes, @AuthenticationPrincipal User user, Model model) {
        ToDoList toDoList = toDoListsRepository.findById(toDoListsId).orElseThrow();
        toDoList.setPlanningDate(planningDate);
        toDoList.setDate(date);
        toDoList.setTitle(title);
        toDoList.setNotes(notes);
        toDoList.setUser(user);
        toDoListsRepository.save(toDoList);
        return "redirect:/toDoListsPage";
    }
    @PostMapping("/toDoListsPage/{toDoListsId}/removeToDoLists")
    private String removeToDo(@PathVariable(value = "toDoListsId") long toDoListsId,@AuthenticationPrincipal String user, Model model) {
        ToDoList toDoList = toDoListsRepository.findById(toDoListsId).orElseThrow();
        toDoListsRepository.delete(toDoList);
        return "redirect:/toDoListsPage";
}
}
