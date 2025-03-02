package com.petr.todo.todoItem;
import java.sql.Date;
import com.petr.todo.todoItem.TodoItem.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTodoItemDTO {

    @NotBlank
    private String title;

    @NotNull
    private Priority priority;

    @NotNull
    private Boolean isDone;

    @NotNull
    private Date created;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "CreateTodoItemDTO [title=" + title + ", priority=" + priority + ", isDone=" + isDone + ", created="
                + created + "]";
    }

    
}
