package com.petr.todo.category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petr.todo.todoItem.TodoItem;

import jakarta.validation.Valid;

@Service
public class CategoryService {

    private CategoryRepository repo;
    private ModelMapper modelMapper;

    private CategoryService(CategoryRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    public List<Category> getAll() {
        return this.repo.findAll();
    }

    public Category createCategory(CreateCategoryDTO data) {
        Category newCategory = modelMapper.map(data, Category.class);
        return this.repo.save(newCategory);
    }

    public Optional<Category> getById(Long id) {
        return this.repo.findById(id);
    }

    public Optional<Category> updateById(Long id, UpdateCategoryDTO data) {
       Optional<Category> result = repo.findById(id);
       if(result.isEmpty()) {
        return result;
       }
       Category foundCategory = result.get();
       this.modelMapper.map(foundCategory, Category.class);
       this.repo.save(foundCategory);
       return Optional.of(foundCategory);
    }

    public boolean deleteById(Long id) {
        Optional<Category> result = this.getById(id);
        if(result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }
}
