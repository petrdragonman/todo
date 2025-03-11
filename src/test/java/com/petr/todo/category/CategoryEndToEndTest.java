package com.petr.todo.category;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.petr.todo.todoItem.TodoItem;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoryEndToEndTest {
    @LocalServerPort
    private int port;

    private ArrayList<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository repo;
   
    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        repo.deleteAll();
        categories.clear();

        // set up data for rests
        // be carefullto make sure you are saving valid data.
        Category category1 =  new Category();
        category1.setTitle("School");
        this.repo.save(category1);
        categories.add(category1);

        Category category2 =  new Category();
        category2.setTitle("Work");
        this.repo.save(category2);
        categories.add(category2);
    }

    // test for get all
    @Test
    public void getAllCategories_CategoriesInDB_ReturnsSuccess() {
        given()
            .when()
            .get("/categories")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(2))
            .body("title", hasItems("School", "Work"));
            //.body(matchesJsonSchemaInClasspath("schemas/all-categories-schema.json"));
    }

    @Test
    public void getAllCategories_NoCategoriesInDB_ReturnsEmptyArray() {
        this.repo.deleteAll();
        given()
            .when()
            .get("/categories")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(0));
            //.body(matchesJsonSchemaInClasspath("schemas/category-schema.json"));
    }

    // tests for GET /categories/:id
    @Test
    public void getById_ValidID_ReturnsCategory() {
        Category firstCategory = categories.get(0);
        given()
            .when()
            .get("/categories/" + firstCategory.getId())
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("title", equalTo("School"));
            //.body(matchesJsonSchemaInClasspath("schemas/category-schema.json"));
    }

    // tests for GET /categories/:id -> INVALID ID
    @Test
    public void getById_InvalidID_BadRequest() {
        given()
            .when()
            .get("/categories/absctrg")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // tests for GET /categories/:id -> not in DB
    @Test
    public void getById_IDNotInDB_NotFound() {
        given()
            .when()
            .get("/categories/9999999")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // ============== VALIDATION TESTS =====================

    @Test
    public void createCategory_EmptyTitle_ReturnsBadRequest() {
        Category category = new Category();
        category.setTitle("");

        given()
            .contentType(ContentType.JSON)
            .body(category)
            .when()
            .post("/categories")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void createCategory_whenPassedPlainText_415() {
        given()
            .contentType(ContentType.TEXT)
            .body("hi")
            .when()
            .post("/categories")
            .then()
            .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    public void createCategory_whenPassedEmptyBody_BadRequest() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/categories")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // @Test void createCategory_WhenPassedBadData_ReturnsBadRequest() {
    //     HashMap<String, String> data = new HashMap<>();
    //     data.put("title", "test");
    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(data)
    //         .when()
    //         .post("/categories")
    //         .then()
    //         .statusCode(HttpStatus.BAD_REQUEST.value());
    // }

}
