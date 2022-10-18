package com.example.toDo.repo;

import com.example.toDo.models.ToDoList;
import com.example.toDo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListsRepository extends JpaRepository<ToDoList,Long> {
    ToDoList findByUser(User user);
}
