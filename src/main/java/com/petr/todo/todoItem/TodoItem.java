package com.petr.todo.todoItem;
import java.sql.Date;
import com.petr.todo.config.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column
    private Boolean isDone;

    @Column
    private Date created;

    public TodoItem() {
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public TodoItem(String title, Priority priority, Boolean isDone, Date created) {
        this.title = title;
        this.priority = priority;
        this.isDone = isDone;
        this.created = created;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

// @Column(nullable = true);
