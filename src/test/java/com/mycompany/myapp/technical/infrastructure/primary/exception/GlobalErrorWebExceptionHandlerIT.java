package com.mycompany.myapp.technical.infrastructure.primary.exception;

import com.mycompany.myapp.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.mycompany.myapp.technical.infrastructure.primary.exception.GlobalErrorWebExceptionHandlerTestController.*;
import static org.hamcrest.core.StringContains.containsString;

@IntegrationTest
@AutoConfigureWebTestClient
public class GlobalErrorWebExceptionHandlerIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldHandleMethodArgumentNotValid() {
    webTestClient
      .post()
      .uri("/api/global-error-web-exception-handler-test/method-argument")
      .body(BodyInserters.fromValue(new TestDataDTO()))
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isBadRequest()
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST.value())
      .jsonPath("$.message").value(containsString("Field error in object 'testDataDTOMono'"))
      .jsonPath("$.timestamp").isNotEmpty();
  }

  @Test
  void shouldHandleMissingServletRequestPartException() {
    webTestClient
      .get()
      .uri("/api/global-error-web-exception-handler-test/missing-servlet-request-part")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isBadRequest()
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST.value())
      .jsonPath("$.message").isEqualTo("Required request part 'part' is not present")
      .jsonPath("$.timestamp").isNotEmpty();
  }

  @Test
  void shouldHandleCustomExceptionWithResponseStatusWithoutDetails() {
    webTestClient
      .get()
      .uri("/api/global-error-web-exception-handler-test/response-status")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isBadRequest()
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST.value())
      .jsonPath("$.message").isEqualTo("test response status reason")
      .jsonPath("$.timestamp").isNotEmpty();
  }

  @Test
  void shouldHandleInternalServerErrorWithoutDetails() {
    webTestClient
      .get()
      .uri("/api/global-error-web-exception-handler-test/internal-server-error")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .jsonPath("$.message").isEqualTo("")
      .jsonPath("$.timestamp").isNotEmpty();
  }

  @Test
  void shouldHandleExceptionContainsPackageNameWithGenericDetail() {

    webTestClient
      .get()
      .uri("/api/global-error-web-exception-handler-test/null-pointer-exception")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .jsonPath("$.message").isEqualTo("java.lang.NullPointerException")
      .jsonPath("$.timestamp").isNotEmpty();
  }


  @Test
  void shouldHandleHttpMessageConversionExceptionWithDetails() {
    webTestClient
      .get()
      .uri("/api/global-error-web-exception-handler-test/http-message-conversion-exception")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
      .expectBody()
      .jsonPath("$.status").isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .jsonPath("$.message").isEqualTo("beer")
      .jsonPath("$.timestamp").isNotEmpty();
  }
}
