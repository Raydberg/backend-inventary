package com.project.service;

import com.project.entity.Category;
import com.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public List<Category> findAllCategory() {
        return repository.findAll();
    }

    public Optional<Category> findOneById(Long id) {
        return repository.findById(id);
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category category) {
        category.setId(id);
        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
