package com.petr.todo.todoItem;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoItemController {

    private TodoItemService todoItemService;
    
    TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public ResponseEntity<TodoItem> postMethodName(@RequestBody @Valid CreateTodoItemDTO data) {
        TodoItem newTodoItem = this.todoItemService.createTodoItem(data);
        return new ResponseEntity<TodoItem>(newTodoItem, HttpStatus.CREATED);
    }

    // 2:13:00

}