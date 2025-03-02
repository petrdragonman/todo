package com.petr.todo.todoItem;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.todo.common.exceptions.NotFoundException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodos() {
        List<TodoItem> todoItems = this.todoItemService.getAll();
        return new ResponseEntity<List<TodoItem>>(todoItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getMethodName(@PathVariable Long id) throws NotFoundException {
        Optional<TodoItem> result = this.todoItemService.getById(id);
        TodoItem founTodoItem = result.orElseThrow(() -> new NotFoundException("Could not find todo item id: " + id));
        return new ResponseEntity<TodoItem>(founTodoItem, HttpStatus.OK);
    }
    
    

}