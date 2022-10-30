package com.mycompany.myapp.authentication.infrastructure.primary;

import com.mycompany.myapp.technical.infrastructure.primary.exception.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
class AuthenticationExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorDTO> handleNotAuthenticateUser(NotAuthenticatedUserException ex, NativeWebRequest request) {
    ErrorDTO errorDTO = ErrorDTO.ErrorDTOBuilder
      .builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.UNAUTHORIZED.value())
      .message("not authenticated")
      .build();

    return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorDTO> handleUnknownAuthentication(UnknownAuthenticationException ex, NativeWebRequest request) {

    ErrorDTO errorDTO = ErrorDTO.ErrorDTOBuilder
      .builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .message("unknown authentication")
      .build();

    return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
