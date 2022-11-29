package com.jitpay.userservice.configuration;

import com.jitpay.userservice.model.exception.ExceptionModel;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Custom handler
 */
@RestControllerAdvice
public class JitPayExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ObjectError> listErrors =  ex.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for(ObjectError error : listErrors){
            errorMessage.append(error.getDefaultMessage());
            errorMessage.append("\n");
        }
        ExceptionModel error = new ExceptionModel(HttpStatus.BAD_REQUEST, "Validation Error", errorMessage.toString());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRepositoryException.class)
    private ResponseEntity<ExceptionModel> handleEntityNotFound(UserRepositoryException ex){
        ExceptionModel error = new ExceptionModel(HttpStatus.NOT_FOUND, "User exception", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
