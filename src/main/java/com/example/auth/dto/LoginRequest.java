package com.example.auth.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
public class LoginRequest {

    @Length(min = 3, max = 20, message = "길이는 3~20자 입니다.")
    private String username;

    @Length(min = 6, max = 40, message = "길이는 6~40자 입니다.")
    private String password;

}
