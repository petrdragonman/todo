package com.petr.todo.category;
import java.util.List;
import com.petr.todo.todoItem.TodoItem;
import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDTO {
    @NotBlank
    private String title;

    private List<TodoItem> todos;

    public String getTitle() {
        return title;
    }

    public List<TodoItem> getTodos() {
        return todos;
    }

    @Override
    public String toString() {
        return "CreateCategoryDTO [title=" + title + ", todos=" + todos + "]";
    }

    
}
