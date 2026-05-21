package com.taskmanager.controller;

import com.taskmanager.dto.ProjectRequest;
import com.taskmanager.model.Project;
import com.taskmanager.model.User;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController
{
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Project createProject(@RequestBody ProjectRequest request)
    {
        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());

        List<User> members =
                userRepository.findAllById(request.getMemberIds());

        project.setMembers(members);

        if(!members.isEmpty())
        {
            project.setCreatedBy(members.get(0));
        }

        return projectRepository.save(project);
    }

    @GetMapping
    public List<Project> getAllProjects()
    {
        return projectRepository.findAll();
    }
}