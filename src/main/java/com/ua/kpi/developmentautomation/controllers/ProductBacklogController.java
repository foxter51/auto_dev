package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.ProductBacklog;
import com.ua.kpi.developmentautomation.services.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product_backlog")
@RequiredArgsConstructor
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklog> getProductBacklog(@PathVariable("id") Long id) {
        Optional<ProductBacklog> productBacklog = productBacklogService.getProductBacklogById(id);
        return productBacklog.isPresent() ? ResponseEntity.ok(productBacklog.get()) : ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductBacklog> updateProductBacklog(@PathVariable Long id, @RequestBody ProductBacklog updatedProductBacklog) {
        return ResponseEntity.ok(productBacklogService.updateProductBacklog(updatedProductBacklog, id));
    }
}
