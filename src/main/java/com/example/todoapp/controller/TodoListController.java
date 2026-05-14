package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todoapp.entity.TodoList;
import com.example.todoapp.service.TodoListService;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;
    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("lists", todoListService.findAll());
        return "list";
    }

    @PostMapping("/list/add")
    public String listAdd(@RequestParam String name) {
        TodoList todoList = new TodoList();
        todoList.setName(name);
        todoListService.addList(todoList);
        return "redirect:/list";
    }

    @PostMapping("/list/{listId}/delete")
    public String deleteList(@PathVariable Long listId) {
        todoListService.deleteList(listId);
        return "redirect:/list";
    }
}
