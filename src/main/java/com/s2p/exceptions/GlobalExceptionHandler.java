package com.s2p.exceptions;

import com.s2p.constants.EOperationStatus;
import com.s2p.core.ErrorResponseDto;
import com.s2p.dto.ApiResponseDto;
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


}