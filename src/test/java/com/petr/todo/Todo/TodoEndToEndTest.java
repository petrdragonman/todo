package com.petr.todo.Todo;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.petr.todo.category.Category;
import com.petr.todo.category.CategoryRepository;
import com.petr.todo.todoItem.TodoItem;
import com.petr.todo.todoItem.TodoItemRepository;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TodoEndToEndTest {
    @LocalServerPort
    private int port;
    private ArrayList<TodoItem> todos = new ArrayList<>();

    @Autowired
    private TodoItemRepository repo;
    
    @Autowired
    private CategoryRepository categoryRepo;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        repo.deleteAll();
        categoryRepo.deleteAll();
        todos.clear();

        // Save the Categories first
        Category workCategory = new Category("work");
        Category schoolCategory = new Category("school");
        categoryRepo.save(workCategory); // Save the Category
        categoryRepo.save(schoolCategory); // Save the Category

        // Create and save TodoItems with the saved Categories
        TodoItem todo1 = new TodoItem();
        todo1.setTitle("do homework");
        todo1.setIsDone(true);
        todo1.setCategory(workCategory); // Associate the saved Category
        this.repo.save(todo1);
        todos.add(todo1);

        TodoItem todo2 = new TodoItem();
        todo2.setTitle("do dishes");
        todo2.setIsDone(false);
        todo2.setCategory(schoolCategory); // Associate the saved Category
        this.repo.save(todo2);
        todos.add(todo2);
    }

    @Test
    public void getAllTodos_TodosInDB_ReturnsSuccess() {
        given().when().get("/todos")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(2))
            .body("title", hasItems("do homework", "do dishes"));
    }

    @Test 
    public void getAllTodos_NoTodosInDB_ReturnsEmptyArray() {
        this.categoryRepo.deleteAll();
        this.repo.deleteAll();
        given() 
            .when()
            .get("/todos")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(0));
    }
}
