package com.sparta.todocard.todo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Entity
@RequiredArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String todoName;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(TodoRequestDto todoRequestDto) {
        this.todoName = todoRequestDto.getBoardName();
        this.writer = todoRequestDto.getWriter();
        this.content = todoRequestDto.getContent();
        this.password = todoRequestDto.getPassword();
    }
    public void update(TodoRequestDto todoRequestDto){
        this.todoName = todoRequestDto.getBoardName();
        this.writer = todoRequestDto.getWriter();
        this.content = todoRequestDto.getContent();
        this.password = todoRequestDto.getPassword();
    }


}
