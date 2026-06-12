package com.example.todoapp.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.TodoList;
import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.form.TaskEditForm;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.TodoListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TodoListRepository todoListRepository;

    private TodoList findTodoListById(Long listId) {
        return todoListRepository.findById(listId)
            .orElseThrow(() -> new ResourceNotFoundException("リストが見つかりません: " + listId));
    }

    @Transactional
    public Task findTaskByIdAndListId(Long taskId, Long listId) {
        return taskRepository.findByIdAndTodoListId(taskId, listId)
            .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません"));
    }

    @Transactional
    public List<Task> findByTodoListId(Long listId) {
        findTodoListById(listId);
        return taskRepository.findByTodoListId(listId);
    }

    @Transactional
    public TaskEditForm findTaskEditForm(Long taskId, Long listId) {
        Task task = findTaskByIdAndListId(taskId, listId);

        TaskEditForm taskEditForm = new TaskEditForm();
        taskEditForm.setContent(task.getContent());
        taskEditForm.setDueDate(task.getDueDate());
        taskEditForm.setDone(task.isDone());

        return taskEditForm;
    }

    @Transactional
    public void createTask(Long listId, String content, LocalDate dueDate) {
        TodoList todoList = findTodoListById(listId);

        Task task = new Task();
        task.setContent(content);
        task.setDueDate(dueDate);
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
