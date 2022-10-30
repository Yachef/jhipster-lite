package com.mycompany.myapp.authentication.infrastructure.primary;

import com.mycompany.myapp.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
class AuthenticationExceptionHandlerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldHandleNotAuthenticatedUserException() throws Exception {
    mockMvc
      .perform(get("/api/account-exceptions/not-authenticated"))
      .andExpect(status().isUnauthorized())
      .andExpect(jsonPath("$.message").value("not authenticated"));
  }

  @Test
  void shouldHandleUnknownAuthenticationException() throws Exception {
    mockMvc
      .perform(get("/api/account-exceptions/unknown-authentication"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.message").value("unknown authentication"));
  }
}
