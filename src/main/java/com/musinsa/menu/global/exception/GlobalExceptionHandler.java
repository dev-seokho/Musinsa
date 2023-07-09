package com.musinsa.menu.global.exception;

import com.musinsa.menu.biz.menu.exception.AlreadyExistsMenuException;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuLinkException;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuTitleException;
import com.musinsa.menu.biz.menu.exception.DuplicateSubMenuTitleException;
import com.musinsa.menu.biz.menu.exception.NoSuchMenuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static String getSimpleName(Exception e) {
        return e.getClass().getSimpleName();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateMenuLinkException.class)
    public ErrorMessage handleDuplicationMenuLinkException(
        DuplicateMenuLinkException e
    ) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(e.getMessage(), getSimpleName(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateMenuTitleException.class)
    public ErrorMessage handleDuplicationMenuTitleException(
        DuplicateMenuTitleException e
    ) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(e.getMessage(), getSimpleName(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsMenuException.class)
    public ErrorMessage handleAlreadyExistsMenuException(
        AlreadyExistsMenuException e
    ) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(e.getMessage(), getSimpleName(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateSubMenuTitleException.class)
    public ErrorMessage handleDuplicationSubMenuTitleException(
        DuplicateSubMenuTitleException e
    ) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(e.getMessage(), getSimpleName(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchMenuException.class)
    public ErrorMessage handleNoSuchMenuException(
        NoSuchMenuException e
    ) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(e.getMessage(), getSimpleName(e));
    }
}
