package com.musinsa.menu.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorMessage {

    private String message;
    private String errorCode;

    public ErrorMessage(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}