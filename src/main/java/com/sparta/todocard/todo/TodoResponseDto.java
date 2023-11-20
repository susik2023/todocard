package com.sparta.todocard.todo;


import lombok.Getter;


public record TodoResponseDto(
        Long id,
        String title,
        String author,
        String content

) {
    public TodoResponseDto(Todo saveTodo) {
        this(
                saveTodo.getId(),
                saveTodo.getTodoName(),
                saveTodo.getWriter(),
                saveTodo.getContent()
        );
    }
}

