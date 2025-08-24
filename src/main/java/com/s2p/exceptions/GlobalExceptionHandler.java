package com.s2p.exceptions;

import com.s2p.constants.EOperationStatus;
import com.s2p.core.ErrorResponseDto;
import com.s2p.dto.ApiResponseDto;
import com.s2p.master.exception.BadRequestException;
import com.s2p.message.EApiResponseMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<ErrorResponseDto>> handleGlobalException(Exception exception,
                                                                                  WebRequest webRequest) {
        log.error("An exception occurred due to : {}", exception.getMessage());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(), LocalDateTime.now());

        ApiResponseDto<ErrorResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(EOperationStatus.RESULT_FAILURE);
        apiResponseDto.setMessage(EApiResponseMessage.INTERNAL_ERROR.getMessage());
        apiResponseDto.setData(errorResponseDto);

        return new ResponseEntity<ApiResponseDto<ErrorResponseDto>>(apiResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponseDto>> handleValidationExceptions(
            MethodArgumentNotValidException exception, WebRequest webRequest) {

        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));

        log.error("Validation failed due to: {}", validationErrors);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                validationErrors.toString(),
                LocalDateTime.now());

        ApiResponseDto<ErrorResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(EOperationStatus.RESULT_FAILURE);
        apiResponseDto.setMessage(EApiResponseMessage.VALIDATION_FAILED.getMessage());
        apiResponseDto.setData(errorResponseDto);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponseDto>> handleConstraintViolationException(
            ConstraintViolationException exception, WebRequest webRequest) {

        String violations = exception.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + " : " + violation.getMessage())
                .collect(Collectors.joining(", "));

        log.error("Constraint violation occurred: {}", violations);

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                violations,
                LocalDateTime.now());

        ApiResponseDto<ErrorResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(EOperationStatus.RESULT_FAILURE);
        apiResponseDto.setMessage(EApiResponseMessage.VALIDATION_FAILED.getMessage());
        apiResponseDto.setData(errorResponseDto);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponseDto>> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {

        log.error("Resource not found: {}", exception.getMessage());

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());

        ApiResponseDto<ErrorResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(EOperationStatus.RESULT_FAILURE);
        apiResponseDto.setMessage(EApiResponseMessage.RESOURCE_NOT_FOUND.getMessage());
        apiResponseDto.setData(errorResponseDto);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponseDto>> handleBadRequestException(
            BadRequestException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now());

        ApiResponseDto<ErrorResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setStatus(EOperationStatus.RESULT_FAILURE);
        apiResponseDto.setMessage(EApiResponseMessage.BAD_REQUEST.getMessage());
        apiResponseDto.setData(errorResponseDto);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
    }


}