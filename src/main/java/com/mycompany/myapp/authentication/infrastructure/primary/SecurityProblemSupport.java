package com.mycompany.myapp.authentication.infrastructure.primary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.mycompany.myapp.common.domain.Generated;

@Component
@Generated
class SecurityProblemSupport implements AuthenticationEntryPoint, AccessDeniedHandler {

  private final HandlerExceptionResolver resolver;

  public SecurityProblemSupport(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, final AuthenticationException exception) {
    resolver.resolveException(request, response, null, exception);
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, final AccessDeniedException exception) {
    resolver.resolveException(request, response, null, exception);
  }
}
