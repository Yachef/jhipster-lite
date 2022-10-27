package com.mycompany.myapp;

import com.mycompany.myapp.common.domain.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
@Generated(reason = "Not testing logs")
public class DummyAppApp {

  private static final Logger log = LoggerFactory.getLogger(DummyAppApp.class);

  public static void main(String[] args) {
    Environment env = SpringApplication.run(DummyAppApp.class, args).getEnvironment();

    if (log.isInfoEnabled()) {
      log.info(ApplicationStartupTraces.of(env));
    }
  }
}
