package com.project.controller;

import com.project.entity.Category;
import com.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        return service.findAllCategory();
    }

    @GetMapping("/categories/{id}")
    public Category findById(@PathVariable Long id) {
        Optional<Category> categoryOpt = service.findOneById(id);
        return categoryOpt.orElseThrow(null);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category createdCategory = service.create(category);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updateCategory = service.update(id, category);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
