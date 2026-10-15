package com.springboot.helpdesk_backend.config;

import com.springboot.helpdesk_backend.dto.response.Errordto;
import com.springboot.helpdesk_backend.exception.InvalidCallException;
import com.springboot.helpdesk_backend.exception.OwnershipException;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger("GlobalExceptionHandler.class");


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Errordto> handleResourceNotFoundException(ResourceNotFoundException e){

        log.warn("Id not found in DB");
        return ResponseEntity
                .badRequest()
        .body(new Errordto(e.getMessage(),
                "ID not found in DB",
                Instant.now())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ){

        Map<String,String> map=new HashMap<>();
        BindingResult bindingResult=e.getBindingResult();
        List<FieldError> list=bindingResult.getFieldErrors();
        list.forEach(fieldError -> {
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                    log.warn("Field {} , failed validation: {}", fieldError.getField(),fieldError.getDefaultMessage());
                }
        );
        return ResponseEntity
                .badRequest()
                .body(map);
    }
    @ExceptionHandler(InvalidCallException.class)
    public ResponseEntity<Errordto> handleInvalidCallException(
            InvalidCallException e
    ){
        //always reply the object not the string

        return ResponseEntity
                .badRequest()
                .body(
                        new Errordto(e.getMessage(),
                                "Call is Invalid due to malformed data",
                                Instant.now())
                );
    }
    @ExceptionHandler(OwnershipException.class)
    public ResponseEntity<Errordto> handleOwnershipException(
            OwnershipException e
    ){
        //always reply the object not the string

        return ResponseEntity
                .badRequest()
                .body(
                        new Errordto(e.getMessage(),
                                "Logged-in User cannot perform this operation",
                                Instant.now())
                );
    }
}
