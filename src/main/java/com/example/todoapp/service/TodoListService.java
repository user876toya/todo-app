package com.example.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todoapp.entity.TodoList;
import com.example.todoapp.repository.TodoListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }
}
