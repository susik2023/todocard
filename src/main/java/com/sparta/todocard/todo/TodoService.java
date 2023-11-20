package com.sparta.todocard.todo;

import com.sparta.todocard.signup.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){

        Todo todo = new Todo(todoRequestDto);

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);
    }
    public List<TodoResponseDto> getAllTodo() {

        return todoRepository.findAll().stream().map(TodoResponseDto::new).toList();
    }
    public Todo getTodo(Long id){
        Todo todo = findTodo();
        return todo;
    }


    @Transactional
    public TodoResponseDto updatedTodo(Long id, TodoRequestDto todoRequestDto ) {

        Todo todo = getUserTodo(id, user);

        todo.setTitle(todoRequestDTO.getTitle());
        todo.setContent(todoRequestDTO.getContent());

        return TodoResponseDto(todo);
    }



    private Todo findTodo(Long id) {

        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 보드가 존재하지 않습니다.")
        );
    }

    public Todo getUserTodo(Long id, User user) {
        Todo todo = findTodo(id);

        if(!user.getId().equals(todo.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return todo;
    }
}
