package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TodoappController {
    @GetMapping("/list")
    public String listPage(Model model) {
        return "list";
    }
}
