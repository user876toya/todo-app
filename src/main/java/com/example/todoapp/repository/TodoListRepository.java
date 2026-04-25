package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

}
