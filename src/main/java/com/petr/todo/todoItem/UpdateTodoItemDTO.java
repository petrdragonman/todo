package com.petr.todo.todoItem;
import jakarta.validation.constraints.Pattern;

public class UpdateTodoItemDTO {
    @Pattern(regexp = ".*\\S.*", message = "Title can not be empty")
    private String title;
    
    //private Priority priority;
    
    private Boolean isDone;

    //private Date created;

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

    // public Date getCreated() {
    //     return created;
    // }

    // public void setCreated(Date created) {
    //     this.created = created;
    // }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
