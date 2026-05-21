package com.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardResponse
{
    private long totalTasks;

    private long completedTasks;

    private long pendingTasks;

    private long overdueTasks;
}