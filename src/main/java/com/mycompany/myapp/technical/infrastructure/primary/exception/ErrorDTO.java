package com.mycompany.myapp.technical.infrastructure.primary.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@SuppressWarnings("unused")
public class ErrorDTO {

  @JsonProperty("status")
  private final int code;
  @JsonProperty("message")
  private final String message;
  @JsonProperty("timestamp")
  private final Date timestamp;

  public ErrorDTO(Map<String, Object> errorPropertiesMap) {
    this.code = (int) errorPropertiesMap.get("status");
    this.message = (String) errorPropertiesMap.get("message");
    this.timestamp = (Date) errorPropertiesMap.get("timestamp");
  }
}
