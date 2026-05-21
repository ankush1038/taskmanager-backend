package com.taskmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest
{
    private String title;

    private String description;

    private String status;

    private String dueDate;

    private Long assignedToId;

    private Long projectId;
}