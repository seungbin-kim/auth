package com.example.auth.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class SignupRequest {

    @Min(value = 3, message = "최소 길이는 3자 입니다.")
    @Max(value = 20, message = "최대 길이는 20자 입니다.")
    private String username;

    @NotNull(message = "필수 입력입니다.")
    @Max(value = 50, message = "최대 길이는 50자 입니다.")
    private String email;

    @NotNull(message = "필수 입력입니다.")
    private List<String> role;

    @Min(value = 6, message = "최소 길이는 6자 입니다.")
    @Max(value = 40, message = "최대 길이는 40자 입니다.")
    private String password;

}
