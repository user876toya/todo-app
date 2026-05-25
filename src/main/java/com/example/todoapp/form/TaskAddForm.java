package com.example.todoapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskAddForm {

    @NotBlank(message = "タスク名を入力してください")
    @Size(max = 255, message = "タスク名は255文字以内で入力してください")
    private String content;
}
