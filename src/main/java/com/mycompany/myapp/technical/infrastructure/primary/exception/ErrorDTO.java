package com.mycompany.myapp.technical.infrastructure.primary.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ErrorDTO {
  @JsonProperty("timestamp")
  private final LocalDateTime timestamp;
  @JsonProperty("status")
  private final int status;
  @JsonProperty("message")
  private final String message;
  @JsonProperty("path")
  private final String path;

  public ErrorDTO(ErrorDTOBuilder errorDTOBuilder) {
    this.timestamp = errorDTOBuilder.timestamp;
    this.status = errorDTOBuilder.status;
    this.message = errorDTOBuilder.message;
    this.path = errorDTOBuilder.path;
  }

  public static class ErrorDTOBuilder {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

    private ErrorDTOBuilder() {
    }

    public static ErrorDTOBuilder builder() {
      return new ErrorDTOBuilder();
    }

    public ErrorDTOBuilder timestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public ErrorDTOBuilder status(int status) {
      this.status = status;
      return this;
    }

    public ErrorDTOBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ErrorDTOBuilder path(String path) {
      this.path = path;
      return this;
    }

    public ErrorDTO build() {
      return new ErrorDTO(this);
    }
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}
