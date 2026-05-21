package com.taskmanager.controller;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.model.*;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public Task createTask(@RequestBody TaskRequest request)
    {
        Task task = new Task();

        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setStatus(
                TaskStatus.valueOf(request.getStatus().toUpperCase())
        );

        task.setDueDate(LocalDate.parse(request.getDueDate()));

        User user =
                userRepository.findById(request.getAssignedToId())
                        .orElseThrow();

        Project project =
                projectRepository.findById(request.getProjectId())
                        .orElseThrow();

        task.setAssignedTo(user);

        task.setProject(project);

        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}/status")
    public Task updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status
    )
    {
        Task task = taskRepository.findById(id)
                .orElseThrow();

        task.setStatus(
                TaskStatus.valueOf(status.toUpperCase())
        );

        return taskRepository.save(task);
    }
}