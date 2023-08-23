package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Project;
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

    private final ProjectRepository repo;
    private final ModelMapper modelMapper;

    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return repo.findById(id);
    }

    public Project saveProject(Project project){
        return repo.save(project);
    }

    public Project updateProject(Project updatedProject, Long oldProjectId) {
        Optional<Project> project = repo.findById(oldProjectId);

        if(project.isPresent()){
            Project oldProject = project.get();

            modelMapper.map(updatedProject, oldProject);
            return oldProject;
        }

        throw new AppException("Project not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteProjectById(Long id){
        repo.deleteById(id);
    }
}
