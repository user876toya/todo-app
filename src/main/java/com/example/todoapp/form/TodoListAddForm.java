package com.example.todoapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoListAddForm {

    private Long id;

    @NotBlank(message = "リスト名を入力してください")
    @Size(max = 100, message = "リスト名は100文字以内で入力してください")
    private String name;
}
