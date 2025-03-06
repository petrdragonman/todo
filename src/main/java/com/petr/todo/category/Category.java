package com.petr.todo.category;

import java.util.ArrayList;
import java.util.List;

import com.petr.todo.common.BaseEntity;
import com.petr.todo.todoItem.TodoItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    
    @Column
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoItem> todos = new ArrayList<>();

    

    public Category(String title) {
        this.title = title;
    }
    public Category() {}

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

    @Override
    public String toString() {
        return "Category [title=" + title + ", todos=" + todos + "]";
    }
}
