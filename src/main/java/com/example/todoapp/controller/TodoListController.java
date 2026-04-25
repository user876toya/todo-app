package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
