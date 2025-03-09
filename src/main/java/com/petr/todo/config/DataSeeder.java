package com.petr.todo.config;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.petr.todo.category.Category;
import com.petr.todo.category.CategoryRepository;
import com.petr.todo.todoItem.TodoItem;
import com.petr.todo.todoItem.TodoItemRepository;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final TodoItemRepository todoRepo;
    private final CategoryRepository categoryRepo;
    private final Faker faker= new Faker();

    public DataSeeder(TodoItemRepository todoRepo, CategoryRepository categoryRepository) {
        this.todoRepo = todoRepo;
        this.categoryRepo = categoryRepository;
    }



    @Override
    public void run(String... args) throws Exception {

        if(categoryRepo.count() == 0) {
            for(int i = 0; i < 2; i++) {
                String title = faker.job().field();
                Category fakeCategory = new Category(title);
                this.categoryRepo.saveAndFlush(fakeCategory);
            }
        }

        // if(todoRepo.count() == 0) {
        //     List<Category> categories = categoryRepo.findAll(); // fetch all categories
        //     if(!categories.isEmpty()) {
        //         for(int i = 0; i < 10; i++) {
        //             String title = faker.job().keySkills();
        //             Category category = categories.get(faker.number().numberBetween(0, categories.size()));
        //             Boolean isDone = faker.number().numberBetween(1, 100) % 2 == 0;
        //             TodoItem fakeTodoItem = new TodoItem(title, category, isDone);
        //             this.todoRepo.saveAndFlush(fakeTodoItem);
        //         }
        //     } else {
        //         System.out.println("no category found....");
        //     }
        // }
 
    }
}
