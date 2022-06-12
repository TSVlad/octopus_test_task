package ru.tsvlad.user.restapi.controller.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tsvlad.user.restapi.controller.advice.exceptions.ConflictException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflict(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationError(MethodArgumentNotValidException e) {

        return new ResponseEntity<>(e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }
}
