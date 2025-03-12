package com.petr.todo.todoItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTodoItemDTO {

    @NotBlank
    private String title;

    @NotNull
    private Boolean isDone;

    private Long categoryId;
    //private String categoryTitle;

    public String getTitle() {
        return title;
    }

    public Boolean getIsDone() {
        return isDone;
    }


    public Long getCategoryId() {
        return categoryId;
    }


    @Override
    public String toString() {
        return "CreateTodoItemDTO [title=" + title + ", isDone=" + isDone + "]";
    }
    
}
