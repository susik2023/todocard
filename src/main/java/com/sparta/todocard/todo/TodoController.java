package com.sparta.todocard.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @PostMapping("/boards")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto){

        return todoService.createTodo(todoRequestDto);
    }
    @GetMapping("/boards")
    public List<TodoResponseDto> getAllTodo(){

        return todoService.getAllTodo();
    }
    @GetMapping("/boards/{id}")
    public Todo getTodo(@PathVariable Long id){

        return todoService.getTodo(id);
    }


    @PutMapping("/boards/{id}")
    public TodoResponseDto updatedTodo(@PathVariable Long id,@RequestBody TodoRequestDto todoRequestDto){

        return todoService.updatedTodo(id, todoRequestDto);
    }





}
