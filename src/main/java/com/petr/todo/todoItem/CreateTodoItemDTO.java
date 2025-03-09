package com.petr.todo.todoItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTodoItemDTO {

    @NotBlank
    private String title;

    // @NotNull
    // private Priority priority;

    @NotNull
    private Boolean isDone;

    //private Long categoryId;
    private String categoryTitle;


    // @NotNull
    // private Date created;

    public String getTitle() {
        return title;
    }

    // public Priority getPriority() {
    //     return priority;
    // }

    public Boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "CreateTodoItemDTO [title=" + title + ", isDone=" + isDone + "]";
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    // public Long getCategoryId() {
    //     return categoryId;
    // }

    // public void setCategoryId(Long categoryId) {
    //     this.categoryId = categoryId;
    // }

    // public Date getCreated() {
    //     return created;
    // }


    
}
