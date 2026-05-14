package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import com.example.todoapp.service.TaskService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @GetMapping("/list/{listId}/tasks")
    public String showTasks(@PathVariable Long listId,
                                Model model) {
        model.addAttribute("tasks",taskService.findByTodoListId(listId));
        return "tasks";
    }
}