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
import com.petr.todo.common.exceptions.ServiceValidationException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
//@CrossOrigin(origins = "http://localhost:5173") // Allow requests from this origin
@RequestMapping("/todos")
public class TodoItemController {
    
    private TodoItemService todoItemService;
    TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody @Valid CreateTodoItemDTO data) throws ServiceValidationException {
        //TodoItem newTodoItem = this.todoItemService.createTodoItem(data);
        TodoItem newTodoItem = this.todoItemService.createTodoItem(data);
        return new ResponseEntity<TodoItem>(newTodoItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodos() {
        List<TodoItem> todoItems = this.todoItemService.getAll();
        return new ResponseEntity<List<TodoItem>>(todoItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getById(@PathVariable Long id) throws NotFoundException {
        Optional<TodoItem> result = this.todoItemService.getById(id);
        TodoItem foundTodoItem = result.orElseThrow(() -> new NotFoundException("Could not find todo item id: " + id));
        return new ResponseEntity<TodoItem>(foundTodoItem, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoItem> updateById(@PathVariable Long id, @Valid @RequestBody UpdateTodoItemDTO data) throws NotFoundException {
        Optional<TodoItem> result = this.todoItemService.updateById(id, data);
        TodoItem updatedTodoItem = result.orElseThrow(() -> new NotFoundException("Could not update todo item id: " + id));
        return new ResponseEntity<TodoItem>(updatedTodoItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NotFoundException {
        boolean wasDeleted = this.todoItemService.deleteById(id);
        if (!wasDeleted) {
            throw new NotFoundException("Could not delete todo item id: " + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}