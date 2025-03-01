package com.petr.todo.todoItem;

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
    
    // 2:13:00
    
}
