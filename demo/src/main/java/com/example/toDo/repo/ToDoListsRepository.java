package com.example.toDo.repo;

import com.example.toDo.models.ToDoLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ToDoListsRepository extends JpaRepository<ToDoLists,Long> {
}
