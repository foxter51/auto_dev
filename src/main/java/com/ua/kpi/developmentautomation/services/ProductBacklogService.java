package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.ProductBacklog;
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

    public Optional<ProductBacklog> getProductBacklogById(Long productBacklogId) {
        return productBacklogRepository.findById(productBacklogId);
    }

    public ProductBacklog updateProductBacklog(ProductBacklog updatedProductBacklog, Long productBacklogId) {
        Optional<ProductBacklog> productBacklog = productBacklogRepository.findById(productBacklogId);
        if (productBacklog.isPresent()) {
            ProductBacklog oldProductBacklog = productBacklog.get();
            modelMapper.map(updatedProductBacklog, oldProductBacklog);
            return oldProductBacklog;
        }
        throw new AppException("Product backlog not found", HttpStatus.BAD_REQUEST);
    }
}
