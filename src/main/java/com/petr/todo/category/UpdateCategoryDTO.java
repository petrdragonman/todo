package com.petr.todo.category;

import java.util.List;

import com.petr.todo.todoItem.TodoItem;

import jakarta.validation.constraints.Pattern;

public class UpdateCategoryDTO {

    @Pattern(regexp = ".*\\S.*", message = "Title can not be empty")
    private String title;

    private List<TodoItem> todos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TodoItem> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoItem> todos) {
        this.todos = todos;
    }
}
