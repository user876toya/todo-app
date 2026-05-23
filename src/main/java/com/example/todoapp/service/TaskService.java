package com.example.todoapp.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.TodoList;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.TodoListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TodoListRepository todoListRepository;

    @Transactional
    public Task findTaskByIdAndListId(Long taskId, Long listId) {
        return taskRepository.findByIdAndTodoListId(taskId, listId)
            .orElseThrow(() -> new IllegalArgumentException("タスクが見つかりません"));
    }

    @Transactional
    public List<Task> findByTodoListId(Long listId) {
        return taskRepository.findByTodoListId(listId);
    }

    @Transactional
    public void createTask(Long listId, String content) {
        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("リストが見つかりません: " + listId));

        Task task = new Task();
        task.setContent(content);
        task.setTodoList(todoList);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long listId, Long taskId) {
        Task task = findTaskByIdAndListId(taskId, listId);
        taskRepository.delete(task);
    }

    @Transactional
    public void updateTask(Long listId, Long taskId, String content, LocalDate dueDate, boolean done) {
        Task task = findTaskByIdAndListId(taskId, listId);
        task.setContent(content);
        task.setDueDate(dueDate);
        task.setDone(done);
    }
}
