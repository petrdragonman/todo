package com.petr.todo.todoItem;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TodoItemService {

    private TodoItemRepository repo;

    TodoItemService(TodoItemRepository repo) {
        this.repo = repo;
    }

    public TodoItem createTodoItem(CreateTodoItemDTO data) {
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setTitle(data.getTitle().trim());
        newTodoItem.setCreated(data.getCreated());
        newTodoItem.setDone(data.isDone());
        newTodoItem.setPriority(data.getPriority());  
        
        return this.repo.save(newTodoItem);
    }

    public List<TodoItem> getAll() {
        return this.repo.findAll();
    }

    public Optional<TodoItem> getById(Long id) {
        return this.repo.findById(id);
    }
    
}
