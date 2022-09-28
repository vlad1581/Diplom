package com.example.toDo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ToDoLists {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoListsId;

    @ManyToOne
    @JoinColumn(name = "user_id_user_id")
    private Users userId;

    @DateTimeFormat
    private String planningDate;

    @DateTimeFormat
    private String date;

    private String title,notes;

    public ToDoLists(String planningDate,String date,String title, String notes) {
        this.planningDate = planningDate;
        this.date = date;
        this.title=title;
        this.notes = notes;
    }


    public ToDoLists() {
    }
}
