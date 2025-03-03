package com.petr.todo.config;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.petr.todo.todoItem.TodoItem;
import com.petr.todo.todoItem.TodoItem.Priority;
import com.petr.todo.todoItem.TodoItemRepository;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final TodoItemRepository todoRepo;
    private final Faker faker= new Faker();

    public DataSeeder(TodoItemRepository todoRepo) {
        this.todoRepo = todoRepo;
    }



    @Override
    public void run(String... args) throws Exception {
        if(todoRepo.count() == 0) {
            System.out.println("in the run...");
            for(int i = 0; i < 10; i++) {
                String title = faker.job().keySkills();
                //Date created = (Date) faker.date().birthday();
                Date created = new Date(4444444);
                Boolean isDone = faker.number().numberBetween(1, 100) % 2 == 0;
                Priority priority = faker.options().option(TodoItem.Priority.class);
                TodoItem fakeTodoItem = new TodoItem(title, priority, isDone, created);
                this.todoRepo.saveAndFlush(fakeTodoItem);
            }
        }
    }
    
}
