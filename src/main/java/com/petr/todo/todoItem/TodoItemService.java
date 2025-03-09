package com.petr.todo.todoItem;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petr.todo.category.Category;
import com.petr.todo.category.CategoryService;
import com.petr.todo.common.ValidationErrors;
import com.petr.todo.common.exceptions.ServiceValidationException;


@Service
public class TodoItemService {

    private TodoItemRepository repo;
    private ModelMapper mapper;
    private CategoryService categoryService;

    TodoItemService(TodoItemRepository repo, ModelMapper mapper, CategoryService categoryService) {
        this.repo = repo;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    public TodoItem createTodoItem(CreateTodoItemDTO data) throws ServiceValidationException {
        // TodoItem newTodoItem = new TodoItem();
        // newTodoItem.setTitle(data.getTitle().trim());
        // newTodoItem.setCreated(data.getCreated());
        // newTodoItem.setIsDone(data.getIsDone());
        // newTodoItem.setPriority(data.getPriority());
        ValidationErrors errors = new ValidationErrors();

        //Category foundCategory = this.categoryService.getById(data.getCategoryId()).orElse(null);
        Category foundCategory = this.categoryService.getByTitle(data.getCategoryTitle()).orElse(null);
        if(foundCategory == null) {
           //errors.addError("category", "Could not find category with id: " + data.getCategoryId());
           errors.addError("category", "Could not find category with id: " + data.getCategoryTitle());
        }
        TodoItem newTodoItem = mapper.map(data, TodoItem.class);
        newTodoItem.setCategory(foundCategory);
        return this.repo.save(newTodoItem);
    }

    public List<TodoItem> getAll() {
        return this.repo.findAll();
    }

    public Optional<TodoItem> getById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<TodoItem> result = this.getById(id);
        if(result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }

    public Optional<TodoItem> updateById(Long id, UpdateTodoItemDTO data) {
        Optional<TodoItem> result = this.getById(id);
        if(result.isEmpty()) {
            return result;
        }
        TodoItem foundTodoItem = result.get();
        // if(data.getTitle() != null) {
        //     foundTodoItem.setTitle(data.getTitle().trim());
        // }
        // if(data.getCreated() != null) {
        //     foundTodoItem.setCreated(data.getCreated());
        // }
        // if(data.getPriority() != null) {
        //     foundTodoItem.setPriority(data.getPriority());
        // }
        // if(data.getPriority() != null) {
        //     foundTodoItem.setIsDone(data.getIsDone());
        // }

        mapper.map(data, foundTodoItem);
        this.repo.save(foundTodoItem);
        return Optional.of(foundTodoItem);
    }
    
}
