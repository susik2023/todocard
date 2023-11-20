package com.sparta.todocard.signup;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignUpRequestDto {
    @Id
    Long userId;
    @Column
    @Size(min = 4,max = 12)
    @NotNull
    private String userName;
    @Column
    @NotNull
    private String passWord;

}
