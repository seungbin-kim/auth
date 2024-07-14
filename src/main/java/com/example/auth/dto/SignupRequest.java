package com.example.auth.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
public class SignupRequest {

    @Length(min = 3, max = 20, message = "길이는 3~20자 입니다.")
    private String username;

    @Length(min = 0, max = 50, message = "길이는 0~50자 입니다.")
    private String email;

    @NotNull(message = "필수 입력입니다.")
    private List<String> role;

    @Length(min = 6, max = 40, message = "길이는 6~40자 입니다.")
    private String password;

}
