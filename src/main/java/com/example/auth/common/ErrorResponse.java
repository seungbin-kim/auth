package com.example.auth.common;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {

    private final String message;

    private final Map<String, String> validations;


    private ErrorResponse(String message,
                          Map<String, String> validations) {

        this.message = message;
        this.validations = validations;
    }


    public static ErrorResponse of(String message,
                                   Map<String, String> validations) {

        return new ErrorResponse(message, validations);
    }

}
