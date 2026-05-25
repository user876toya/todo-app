package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.form.TodoListAddForm;
import com.example.todoapp.service.TodoListService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;
    @GetMapping("/lists")
    public String listPage(Model model) {
        model.addAttribute("lists", todoListService.findAll());
        model.addAttribute("todoListAddForm", new TodoListAddForm());
        return "list";
    }

    @PostMapping("/lists")
    public String listAdd(@Valid TodoListAddForm todoListAddForm,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("lists", todoListService.findAll());
            model.addAttribute("todoListAddForm", todoListAddForm);
            return "list";
        }
        todoListService.addList(todoListAddForm.getName());
        return "redirect:/lists";
    }

    @PostMapping("/lists/{listId}/delete")
    public String deleteList(@PathVariable Long listId) {
        todoListService.deleteList(listId);
        return "redirect:/lists";
    }
}
