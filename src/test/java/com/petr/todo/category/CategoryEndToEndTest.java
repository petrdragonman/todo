package com.petr.todo.category;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.github.javafaker.Book;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


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
        // arrange -> done in beforeEach
        given().when().get("/categories")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("$", hasSize(2))
        .body("title", hasItems("School", "Work"));
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

    }
}
