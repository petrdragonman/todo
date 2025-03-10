package com.petr.todo.Todo;

import java.util.ArrayList;
import java.util.HashMap;

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
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


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
        categoryRepo.save(workCategory); 
        categoryRepo.save(schoolCategory); 

        // Create and save TodoItems with the saved Categories
        TodoItem todo1 = new TodoItem();
        todo1.setTitle("do homework");
        todo1.setIsDone(true);
        todo1.setCategory(workCategory); 
        this.repo.save(todo1);
        todos.add(todo1);

        TodoItem todo2 = new TodoItem();
        todo2.setTitle("do dishes");
        todo2.setIsDone(false);
        todo2.setCategory(schoolCategory); 
        this.repo.save(todo2);
        todos.add(todo2);
    }

    @Test
    public void getAllTodos_TodosInDB_ReturnsSuccess() {
        given()
            .when()
            .get("/todos")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(2))
            .body("title", hasItems("do homework", "do dishes"))
            .body("category.title", hasItems("work", "school"));
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

    // tests for GET /todos/:id
    @Test
    public void getById_ValidID_ReturnsTodo() {
        TodoItem firstTodo = todos.get(0);
        given()
            .when()
            .get("/todos/" + firstTodo.getId())
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("title", equalTo("do homework"))
            .body("isDone", equalTo(true))
            .body("category.title", equalTo("work"));
            //.body(matchesJsonSchemaInClasspath("/schemas/todo-schema.json"));
    }

    // tests for GET /todos/:id -> INVALID ID
    @Test
    public void getById_InvalidID_BadRequest() {
        given()
            .when()
            .get("/todos/abscrtf")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // tests for GET /todos/:id -> not in the DB
    @Test
    public void getById_IDNotInDB_NotFound() {
        given()
            .when()
            .get("/todos/9999999")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // ============== UPDATE ===============================
    // @Test
    // public void updateTodoItem_ValidData_ReturnsUpdatedTodoItem() {
    //     TodoItem todo = todos.get(0);
    //     todo.setTitle("Updated Title");

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(todo)
    //         .when()
    //         .put("/todos/" + todo.getId())
    //         .then()
    //         .statusCode(HttpStatus.OK.value())
    //         .body("title", equalTo("Updated Title"));
    // }

    // @Test
    // public void updateTodoItem_NonExistentId_ReturnsNotFound() {
    //     TodoItem todo = new TodoItem();
    //     todo.setTitle("Updated Title");

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(todo)
    //         .when()
    //         .put("/todos/9999999")
    //         .then()
    //         .statusCode(HttpStatus.NOT_FOUND.value());
    // }



    // ============== VALIDATION TESTS =====================

    @Test
    public void createTodoItem_EmptyTitle_ReturnsBadRequest() {
        TodoItem todo = new TodoItem();
        todo.setTitle("");

        given()
            .contentType(ContentType.JSON)
            .body(todo)
            .when()
            .post("/todos")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void createTodo_whenPassedPlainText_415() {
        given()
            .contentType(ContentType.TEXT)
            .body("hi")
            .when()
            .post("/todos")
            .then()
            .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    public void createTodo_whenPassedEmptyBody_BadRequest() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/todos")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // @Test void createTodo_WhenPassedBadData_ReturnsBadRequest() {
    //     HashMap<String, String> data = new HashMap<>();
    //     data.put("title", "do test");
    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(data)
    //         .when()
    //         .post("/todos")
    //         .then()
    //         .statusCode(HttpStatus.BAD_REQUEST.value());
    // }
}
