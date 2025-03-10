package com.petr.todo.todoItem;
import jakarta.validation.constraints.Pattern;

public class UpdateTodoItemDTO {
    @Pattern(regexp = ".*\\S.*", message = "Title can not be empty")
    private String title;
    
    //private Priority priority;
    
    private Boolean isDone;

    //private Long categoryId;
    private String categoryTitle;

    //private Date created;

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

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
