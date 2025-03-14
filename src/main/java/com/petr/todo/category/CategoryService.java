package com.petr.todo.category;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository repo;
    private ModelMapper mapper;

    private CategoryService(CategoryRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<Category> getAll() {
        return this.repo.findAll();
    }

    public Category createCategory(CreateCategoryDTO data) {
        Category newCategory = mapper.map(data, Category.class);
        return this.repo.save(newCategory);
    }

    public Optional<Category> getById(Long id) {
        return this.repo.findById(id);
    }

    public Optional<Category> getByTitle(String categoryTitle) {
        return this.repo.findByTitle(categoryTitle);
    }

    public Optional<Category> updateById(Long id, UpdateCategoryDTO data) {
       Optional<Category> result = repo.findById(id);
       if(result.isEmpty()) {
        return result;
       }
       Category foundCategory = result.get();
       this.mapper.map(foundCategory, Category.class);
       this.repo.save(foundCategory);
       return Optional.of(foundCategory);
    }

    public boolean deleteById(Long id) {
        Optional<Category> result = this.getById(id);
        if(result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }

    public String toCapitalise(String categoryTitle) {
        return categoryTitle.substring(0, 1).toUpperCase() + categoryTitle.substring(1).toLowerCase();
    }

    
}
