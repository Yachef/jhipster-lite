package com.mycompany.myapp.technical.infrastructure.primary.exception;

import com.mycompany.myapp.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerIT {

  @Autowired
  ApplicationContext applicationContext;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldHandleMethodArgumentNotValid() throws Exception {
    mockMvc
      .perform(post("/api/exception-translator-test/method-argument").content("{}").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value(containsString("Validation failed for argument")))
      .andExpect(jsonPath("$.path").value("/api/exception-translator-test/method-argument"))
      .andExpect(jsonPath("$.timestamp").isNotEmpty());
  }

  @Test
  void shouldMissingServletRequestPartException() throws Exception {
    mockMvc
      .perform(get("/api/exception-translator-test/missing-servlet-request-part"))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Required request part 'part' is not present"))
      .andExpect(jsonPath("$.path").value("/api/exception-translator-test/missing-servlet-request-part"));
  }

  @Test
  void shouldHandleMissingServletRequestParameterException() throws Exception {
    mockMvc
      .perform(get("/api/exception-translator-test/missing-servlet-request-parameter"))
      .andExpect(status().isBadRequest());
  }

  @Test
  void shouldHandleInternalServerError() throws Exception {
    mockMvc
      .perform(get("/api/exception-translator-test/internal-server-error"))
      .andExpect(status().isInternalServerError());
  }

  @Test
  void shouldHandleHttpMessageConversionException() throws Exception {
    mockMvc
      .perform(get("/api/exception-translator-test/http-message-conversion-exception"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.message").value("beer"));
  }

  @Test
  void shouldHandleExceptionContainsPackageName() throws Exception {
    mockMvc
      .perform(get("/api/exception-translator-test/null-pointer-exception"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.message").value("java.lang.NullPointerException"));
  }
}
