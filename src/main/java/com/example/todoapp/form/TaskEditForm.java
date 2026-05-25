package com.example.todoapp.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskEditForm {

    @NotBlank(message = "タスク名を入力してください")
    @Size(max = 255, message = "タスク名は255文字以内で入力してください")
    private String content;
    
    @NotNull(message = "期限日を選択してください")
    private LocalDate dueDate;

    private boolean done;
}
