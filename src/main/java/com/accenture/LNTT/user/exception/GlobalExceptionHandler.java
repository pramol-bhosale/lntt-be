package com.accenture.LNTT.user.exception;

import com.accenture.LNTT.common.dto.ValidationDTO;
import com.accenture.LNTT.common.enums.Constants;
import com.accenture.LNTT.common.util.ResultBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgNotValidException) {

        List<ValidationDTO> validationDTOS = methodArgNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream().map(fieldError -> new ValidationDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();


        return new ResponseEntity<>(ResultBuilder.failure(Constants.VALIDATION_FAILED, validationDTOS), HttpStatus.BAD_REQUEST);
    }
}
