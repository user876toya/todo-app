package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.form.TaskAddForm;
import com.example.todoapp.form.TaskEditForm;
import com.example.todoapp.service.TaskService;

import jakarta.validation.Valid;
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
        model.addAttribute("taskAddForm", new TaskAddForm());
        return "tasks";
    }

    @GetMapping("/lists/{listId}/tasks/{taskId}/edit")
    public String showTaskEdit(@PathVariable Long listId,
                               @PathVariable Long taskId,
                               Model model) {

        model.addAttribute("listId", listId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("taskEditForm", taskService.findTaskEditForm(taskId, listId));
        return "task-edit";
    }

    @PostMapping("/lists/{listId}/tasks")
    public String createTask(@PathVariable Long listId,
                             @Valid TaskAddForm taskAddForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listId", listId);
            model.addAttribute("tasks", taskService.findByTodoListId(listId));
            model.addAttribute("taskAddForm", taskAddForm);
            return "tasks";
        }

        taskService.createTask(listId, taskAddForm.getContent(), taskAddForm.getDueDate());
        return "redirect:/lists/{listId}/tasks";
    }

    @PostMapping("/lists/{listId}/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Long listId,
                             @PathVariable Long taskId) {
        taskService.deleteTask(listId, taskId);
        return "redirect:/lists/{listId}/tasks";
    }
    

    @PostMapping("/lists/{listId}/tasks/{taskId}/edit")
    public String editTask(@PathVariable Long listId,
                           @PathVariable Long taskId,
                           @Valid TaskEditForm taskEditForm,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listId", listId);
            model.addAttribute("taskId", taskId);
            model.addAttribute("taskEditForm", taskEditForm);
            return "task-edit";
        }

        taskService.updateTask(listId, taskId, taskEditForm.getContent(), taskEditForm.getDueDate(), taskEditForm.isDone());
        return "redirect:/lists/{listId}/tasks";
    }

}