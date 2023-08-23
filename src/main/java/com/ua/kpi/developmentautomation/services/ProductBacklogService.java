package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.ProductBacklog;
import com.ua.kpi.developmentautomation.entities.Project;
import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.ProductBacklogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;
    private final ModelMapper modelMapper;

    private final CustomUserDetailsService userDetailsService;
    private final ProjectService projectService;

    public Optional<ProductBacklog> getProductBacklogById(Long id) {
        return productBacklogRepository.findById(id);
    }

    public ProductBacklog saveProductBacklog(Long ownerId, Long projectId, ProductBacklog productBacklog) {
        Optional<User> owner = userDetailsService.getUserById(ownerId);
        Optional<Project> project = projectService.getProjectById(projectId);

        if(owner.isPresent() && project.isPresent()){
            productBacklog.setOwner(owner.get());
            productBacklog.setProject(project.get());
            return productBacklogRepository.save(productBacklog);
        }

        throw new AppException("Owner or parent project not found", HttpStatus.BAD_REQUEST);
    }

    public ProductBacklog updateProductBacklog(ProductBacklog updatedProductBacklog, Long id) {
        Optional<ProductBacklog> productBacklog = productBacklogRepository.findById(id);
        if (productBacklog.isPresent()) {
            ProductBacklog oldProductBacklog = productBacklog.get();
            modelMapper.map(updatedProductBacklog, oldProductBacklog);
            return oldProductBacklog;
        }
        throw new AppException("Product backlog not found", HttpStatus.BAD_REQUEST);
    }
}
