package com.petr.todo.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

public class CategoryServiceTest {
    // take care of dependencies -> fake repo and mapper
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;

    // spy - watch category service
    @Spy
    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAll_callsFindAll(){
        categoryService.getAll();
        verify(categoryRepository).findAll();
    }

    @Test
    public void getById_callsGetById(){
        categoryService.getById(1L);
        verify(categoryRepository).findById(1L);
    }

    @Test
    public void createCategory_repoSavesMappedClass() {
        // given
        CreateCategoryDTO emptyDto = new CreateCategoryDTO();
        Category mockCategory = new Category();
        // when
        when(modelMapper.map(emptyDto, Category.class)).thenReturn(mockCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);
        // act
        Category result = categoryService.createCategory(emptyDto);
        // assert
        verify(categoryRepository).save(mockCategory);
        assertNotNull(result);
        assertEquals(mockCategory, result);
    }
    

}
