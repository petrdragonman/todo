package com.petr.todo.todoItem;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class TodoItemService {

    private TodoItemRepository repo;
    private ModelMapper mapper;

    TodoItemService(TodoItemRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public TodoItem createTodoItem(CreateTodoItemDTO data) {
        // TodoItem newTodoItem = new TodoItem();
        // newTodoItem.setTitle(data.getTitle().trim());
        // newTodoItem.setCreated(data.getCreated());
        // newTodoItem.setIsDone(data.getIsDone());
        // newTodoItem.setPriority(data.getPriority());  
        TodoItem newTodoItem = mapper.map(data, TodoItem.class);
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
        if(data.getTitle() != null) {
            foundTodoItem.setTitle(data.getTitle().trim());
        }
        if(data.getCreated() != null) {
            foundTodoItem.setCreated(data.getCreated());
        }
        if(data.getPriority() != null) {
            foundTodoItem.setPriority(data.getPriority());
        }
        if(data.getPriority() != null) {
            foundTodoItem.setIsDone(data.getIsDone());
        }

        this.repo.save(foundTodoItem);
        return Optional.of(foundTodoItem);
    }
    
}
