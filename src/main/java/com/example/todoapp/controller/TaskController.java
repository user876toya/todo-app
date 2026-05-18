package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todoapp.service.TaskService;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @GetMapping("/list/{listId}/tasks")
    public String showTasks(@PathVariable Long listId,
                                Model model) {
        model.addAttribute("listId", listId);
        model.addAttribute("tasks",taskService.findByTodoListId(listId));
        return "tasks";
    }

    @PostMapping("/lists/{listId}/tasks")
    public String createTask(@PathVariable Long listId,
                             @RequestParam String content,
                             Model model) {
        taskService.createTask(listId, content);
        return "redirect:/list/{listId}/tasks";
    }
}
