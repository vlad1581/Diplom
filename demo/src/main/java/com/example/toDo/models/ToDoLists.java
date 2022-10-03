package com.example.toDo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ToDoLists {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoListsId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @DateTimeFormat
    private String planningDate;

    @DateTimeFormat
    private String date;

    private String title;
    private String notes;

    public ToDoLists(String planningDate,String date,String title, String notes) {
        this.planningDate = planningDate;
        this.date = date;
        this.title=title;
        this.notes = notes;
    }


    public ToDoLists() {
    }
}
