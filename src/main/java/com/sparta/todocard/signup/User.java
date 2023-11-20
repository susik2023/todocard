package com.sparta.todocard.signup;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table(name = "Users")
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 4,max = 12)
    @NotBlank
    private String userName;

    @Column
    @NotBlank
    private String passWord;


//    public User(SignUpRequestDto requestDto) {
//        this.userName = requestDto.getUserName();
//        this.passWord = requestDto.getPassWord();
//    }
    public User(String username, String password) {
        this.userName = username;
        this.passWord = password;

    }

}
