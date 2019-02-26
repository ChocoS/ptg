package com.pwawrzyniak.pythagorastreegenerator.server;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pwawrzyniak.pythagorastreegenerator")
@EnableVaadin("com.pwawrzyniak.pythagorastreegenerator")
public class PythagorasTreeGeneratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(PythagorasTreeGeneratorApplication.class, args);
  }
}