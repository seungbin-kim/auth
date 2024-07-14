package com.example.auth.controller;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "인증 API")
public interface AuthControllerDocs {

    @Operation(
            summary = "회원가입",
            description = "회원가입 합니다.",
            requestBody = @RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "예시", value = """
                                    {
                                        "username": "example",
                                        "email": "email@gmail.com",
                                        "role": ["ROLE_USER"],
                                        "password": "myPassword"
                                    }
                                    """)
                    }
            ))
    )
    ResponseEntity<Void> signup(SignupRequest signupRequest);

    @Operation(
            summary = "로그인",
            description = "로그인 합니다.",
            requestBody = @RequestBody(content = @Content(
                    examples = {
                            @ExampleObject(name = "예시", value = """
                                    {
                                        "username": "example",
                                        "password": "myPassword"
                                    }
                                    """)
                    }
            ))
    )
    ResponseEntity<Void> login(LoginRequest loginRequest);

    @Operation(
            summary = "로그아웃",
            description = "로그아웃 합니다."
    )
    ResponseEntity<Void> logout(HttpServletResponse response);
}
