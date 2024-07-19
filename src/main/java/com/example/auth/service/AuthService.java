package com.example.auth.service;

import com.example.auth.common.ApiException;
import com.example.auth.common.ErrorCode;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;
import com.example.auth.entity.Roles;
import com.example.auth.entity.User;
import com.example.auth.entity.UsersRoles;
import com.example.auth.repository.RolesRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    private final UserRolesRepository userRolesRepository;

    private final RolesRepository rolesRepository;

    @Transactional
    public void signup(SignupRequest signupRequest) {

        // 이메일 중복 검사
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ApiException(ErrorCode.DUPLICATED_EMAIL);
        }
        // USERNAME 중복 검사
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new ApiException(ErrorCode.DUPLICATED_USERNAME);
        }

        // role 검색
        List<Roles> roles = rolesRepository.findByNameIn(signupRequest.getRole());
        if (signupRequest.getRole().size() != roles.size()) { // 입력 role과 찾은 role 개수가 같아야 함!
            throw new ApiException(ErrorCode.ROLE_NOT_FOUND);
        }

        // 유저 저장
        User saved = userRepository.save(
                new User(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getUsername())
        );

        // user role 저장
        roles.forEach(role -> userRolesRepository.save(new UsersRoles(role, saved)));
    }

    public User login(LoginRequest loginRequest) {

        return userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    public List<String> getRoles(User user) {

        return userRolesRepository.findByUser(user)
                .stream()
                .map(usersRole -> usersRole.getRoles().getName())
                .toList();
    }
}
