package com.assignment.transactions.exception;

import com.assignment.transactions.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> BadRequestHandler(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }


}
