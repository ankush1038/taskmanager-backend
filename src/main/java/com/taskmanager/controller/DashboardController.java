package com.taskmanager.controller;

import com.taskmanager.dto.DashboardResponse;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
public class DashboardController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public DashboardResponse getDashboard()
    {
        long totalTasks =
                taskRepository.count();

        long completedTasks =
                taskRepository.countByStatus(TaskStatus.DONE);

        long pendingTasks =
                taskRepository.countByStatus(TaskStatus.TODO)
                        +
                        taskRepository.countByStatus(TaskStatus.IN_PROGRESS);

        long overdueTasks =
                taskRepository.countByDueDateBeforeAndStatusNot(
                        LocalDate.now(),
                        TaskStatus.DONE
                );

        return new DashboardResponse(
                totalTasks,
                completedTasks,
                pendingTasks,
                overdueTasks
        );
    }
}