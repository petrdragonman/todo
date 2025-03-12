package com.petr.todo.todoItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.petr.todo.category.Category;
import com.petr.todo.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class TodoItem extends BaseEntity {

    // public enum Priority {
    //     HIGH,
    //     MEDIUM, 
    //     LOW
    // }

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Foreign key in the Categories table
    @JsonIgnoreProperties({"category", "todos"})
    private Category category;
    
    // @Enumerated(EnumType.STRING)
    // private Priority priority;

    @Column
    private Boolean isDone = false;

    public TodoItem(String title, Category category, Boolean isDone) {}

    public TodoItem() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "TodoItem [title=" + title + ", isDone=" + isDone + "]";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
