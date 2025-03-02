package com.petr.todo.todoItem;

import java.sql.Date;

import com.petr.todo.todoItem.TodoItem.Priority;
import jakarta.validation.constraints.Pattern;

public class UpdateTodoItemDTO {
    @Pattern(regexp = ".*\\S.*", message = "Title can not be empty")
    private String title;
    
    private Priority priority;
    
    private boolean isDone;

    private Date created;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}
