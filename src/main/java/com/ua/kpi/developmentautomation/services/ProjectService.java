package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.ProductBacklog;
import com.ua.kpi.developmentautomation.entities.Project;
import com.ua.kpi.developmentautomation.entities.Sprint;
import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    private final CustomUserDetailsService userDetailsService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public Project updateProject(Project updatedProject, Long oldProjectId) {
        Optional<Project> project = projectRepository.findById(oldProjectId);

        if(project.isPresent()){
            Project oldProject = project.get();
            modelMapper.map(updatedProject, oldProject);
            return oldProject;
        }

        throw new AppException("Project not found", HttpStatus.BAD_REQUEST);
    }

    public Project updateProjectProductBacklog(Long ownerId, Long projectId, ProductBacklog productBacklog) {
        Optional<User> owner = userDetailsService.getUserById(ownerId);
        Optional<Project> oldProject = getProjectById(projectId);

        if(owner.isPresent() && oldProject.isPresent()){
            productBacklog.setOwner(owner.get());
            Project project = oldProject.get();
            project.setProductBacklog(productBacklog);
            return projectRepository.save(project);
        }

        throw new AppException("Owner or project not found", HttpStatus.BAD_REQUEST);
    }

    public Project updateProjectSprints(Long projectId, Sprint sprint) {
        Optional<Project> oldProject = getProjectById(projectId);

        if(oldProject.isPresent()){
            Project project = oldProject.get();
            project.addSprint(sprint);
            return projectRepository.save(project);
        }

        throw new AppException("Parent project not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteProjectById(Long projectId){
        projectRepository.deleteById(projectId);
    }
}
