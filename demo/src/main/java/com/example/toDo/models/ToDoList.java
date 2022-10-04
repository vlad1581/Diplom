package com.example.toDo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoListsId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat
    private String planningDate;

    @DateTimeFormat
    private String date;

    private String title;
    private String notes;

    public ToDoList(String planningDate, String date, String title, String notes,User user) {
        this.planningDate = planningDate;
        this.date = date;
        this.title=title;
        this.notes = notes;
        this.user=user;
    }


    public ToDoList() {
    }
}
