package com.mycompany.myapp.technical.infrastructure.primary.exception;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exception-translator-test")
public class GlobalExceptionHandlerTestController {

  @PostMapping("/method-argument")
  public void methodArgument(@Valid @RequestBody TestDTO testDTO) {}

  @GetMapping("/missing-servlet-request-part")
  public void missingServletRequestPartException(@RequestPart String part) {}

  @GetMapping("/missing-servlet-request-parameter")
  public void missingServletRequestParameterException(@RequestParam String param) {}

  @GetMapping("/internal-server-error")
  public void internalServerError() {
    throw new RuntimeException();
  }

  @GetMapping("/null-pointer-exception")
  public void nullPointerException() {
    throw new NullPointerException("java.lang.NullPointerException");
  }

  @GetMapping("/http-message-conversion-exception")
  public void httpMessageConversionException() {
    throw new HttpMessageConversionException("beer");
  }

  public static class TestDTO {

    @NotNull
    private String test;

    public String getTest() {
      return test;
    }

    public void setTest(String test) {
      this.test = test;
    }
  }
}
