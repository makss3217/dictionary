package com.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class DictionaryApplication {

  public static void main(String[] args) {
    SpringApplication.run(DictionaryApplication.class, args);

  }

}
