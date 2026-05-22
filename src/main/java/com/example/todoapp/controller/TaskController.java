package com.example.todoapp.controller;

import java.time.LocalDate;

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
    @GetMapping("/lists/{listId}/tasks")
    public String showTasks(@PathVariable Long listId,
                                Model model) {
        model.addAttribute("listId", listId);
        model.addAttribute("tasks",taskService.findByTodoListId(listId));
        return "tasks";
    }

    @GetMapping("/lists/{listId}/tasks/{taskId}/edit")
    public String showTaskEdit(@PathVariable Long listId,
                               @PathVariable Long taskId,
                               Model model) {

        model.addAttribute("listId", listId);
        model.addAttribute("task", taskService.findTaskByIdAndListId(taskId, listId));
        return "task-edit";
    }

    @PostMapping("/lists/{listId}/tasks")
    public String createTask(@PathVariable Long listId,
                             @RequestParam String content) {
        taskService.createTask(listId, content);
        return "redirect:/lists/{listId}/tasks";
    }

    @PostMapping("/lists/{listId}/tasks/{taskId}/edit")
    public String editTask(@PathVariable Long listId,
                           @PathVariable Long taskId,
                           @RequestParam String content,
                           @RequestParam LocalDate dueDate,
                           @RequestParam boolean done) {

        taskService.updateTask(listId, taskId, content, dueDate, done);
        return "redirect:/lists/{listId}/tasks";
    }

}
