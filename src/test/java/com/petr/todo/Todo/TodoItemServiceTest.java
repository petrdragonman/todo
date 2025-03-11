package com.petr.todo.Todo;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import com.petr.todo.todoItem.TodoItemRepository;
import com.petr.todo.todoItem.TodoItemService;

public class TodoItemServiceTest {
    // take care of dependencies -> fake repo and mapper
    @Mock
    private TodoItemRepository todoItemRepository;
    @Mock
    private ModelMapper modelMapper;

    // spy - watch category service
    @Spy
    @InjectMocks
    private TodoItemService todoItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAll_callsFindAll(){
        todoItemService.getAll();
        verify(todoItemRepository).findAll();
    }

    @Test
    public void getById_callsFindById(){
        todoItemService.getById(1L);
        verify(todoItemRepository).findById(1L);
    }
}
