package com.example.todoapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTodoListId(Long listId);

    Optional<Task> findByIdAndTodoListId(Long taskId, Long listId);

    void deleteByTodoListId(Long listId);
}
