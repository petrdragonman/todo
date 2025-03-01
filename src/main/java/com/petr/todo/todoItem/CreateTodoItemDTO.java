package com.petr.todo.todoItem;
import java.sql.Date;
import com.petr.todo.todoItem.TodoItem.Priority;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTodoItemDTO {

    @NotBlank
    private String title;

    @NotNull
    private Priority priority;

    @NotBlank
    private boolean isDone;

    @NotNull
    @Min(0)
    private Date created;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isDone() {
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
