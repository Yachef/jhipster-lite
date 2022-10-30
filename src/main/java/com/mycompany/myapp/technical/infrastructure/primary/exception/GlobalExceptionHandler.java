package com.mycompany.myapp.technical.infrastructure.primary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({HttpMessageNotReadableException.class,
    MultipartException.class, MissingServletRequestPartException.class,
    MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorDTO> badRequestExceptionHandler(Exception ex, HttpServletRequest request) {
    ErrorDTO errorDTO = ErrorDTO.ErrorDTOBuilder.builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.BAD_REQUEST.value())
      .path(request.getRequestURI())
      .message(ex.getMessage())
      .build();

    return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception ex, HttpServletRequest request) {
    ErrorDTO errorDTO = ErrorDTO.ErrorDTOBuilder.builder()
      .timestamp(LocalDateTime.now())
      .status(INTERNAL_SERVER_ERROR.value())
      .path(request.getRequestURI())
      .message(ex.getMessage())
      .build();

    return new ResponseEntity<>(errorDTO, INTERNAL_SERVER_ERROR);
  }
}
