package com.example.auth.controller;

import com.example.auth.common.TokenUtils;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;
import com.example.auth.entity.User;
import com.example.auth.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    private final TokenUtils tokenUtils;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest signupRequest) {

        authService.signup(signupRequest);

        return ResponseEntity.ok().build();
    }


    @Override
    @PostMapping("/signin")
    public ResponseEntity<Void> login(LoginRequest loginRequest) {

        User user = authService.login(loginRequest);
        List<String> roles = authService.getRoles(user);

        String jwt = tokenUtils.createJwt(user.getId(), roles);

        ResponseCookie accessToken = ResponseCookie.from("accessToken", jwt)
                .httpOnly(true)
                .path("/")
                .maxAge(tokenUtils.getExpirationTime())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessToken.toString())
                .build();
    }

    @Override
    @PostMapping("/signout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok()
                .build();
    }

}
