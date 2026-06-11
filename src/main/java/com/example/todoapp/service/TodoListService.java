package com.example.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todoapp.entity.TodoList;
import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.TodoListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList findById(Long listId) {
        return todoListRepository.findById(listId)
            .orElseThrow(() -> new ResourceNotFoundException("リストが見つかりません: " + listId));
    }

    @Transactional
    public void addList(String listName) {
        
        TodoList todoList = new TodoList();
        todoList.setName(listName);

        todoListRepository.save(todoList);
    }

    @Transactional
    public void deleteList(Long listId) {

        TodoList todoList = findById(listId);
        taskRepository.deleteByTodoListId(listId);
        todoListRepository.delete(todoList);
    }
}
