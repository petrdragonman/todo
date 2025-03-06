package com.petr.todo.todoItem;
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

    public enum Priority {
        HIGH,
        MEDIUM, 
        LOW
    }

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key in the Categories table
    private Category category;
    
    // @Enumerated(EnumType.STRING)
    // private Priority priority;

    @Column
    private Boolean isDone = false;



    // @Column
    // private Date created;

    public TodoItem(String title, Category category, Boolean isDone) {}

    public TodoItem() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // public Priority getPriority() {
    //     return priority;
    // }

    // public void setPriority(Priority priority) {
    //     this.priority = priority;
    // }

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

    // public Date getCreated() {
    //     return created;
    // }

    // public void setCreated(Date created) {
    //     this.created = created;
    // }

    
}

// @Column(nullable = true);
