package com.taskmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectRequest
{
    private String name;

    private String description;

    private List<Long> memberIds;
}