package com.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}