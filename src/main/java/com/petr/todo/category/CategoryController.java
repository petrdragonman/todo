package com.petr.todo.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.todo.common.exceptions.NotFoundException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO data) {
        Category newCategory = this.categoryService.createCategory(data);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping
    //public ResponseEntity<List<Category>> getAllCategories(@RequestBody String entity) {
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryService.getAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) throws NotFoundException {
        Optional<Category> result = this.categoryService.getById(id);
        Category foundCategory = result.orElseThrow(() -> new NotFoundException("Could not find category item id: " + id));
        return new ResponseEntity<Category>(foundCategory, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateById(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO data) throws NotFoundException {
        Optional<Category> result = this.categoryService.updateById(id, data);
        Category updatedCategory = result.orElseThrow(() -> new NotFoundException("Could not update category item id: " + id));
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NotFoundException {
        boolean wasDeleted = this.categoryService.deleteById(id);
        if(!wasDeleted) {
            throw new NotFoundException("Could not delete category item id: " + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
