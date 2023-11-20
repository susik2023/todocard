package com.sparta.todocard.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class TodoRequestDto {

    private String boardName;
    private String writer;
    private String content;
    private String password;
}
