package com.petr.todo.todoItem;
//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    
}
