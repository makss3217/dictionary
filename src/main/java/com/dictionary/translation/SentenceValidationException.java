package com.dictionary.translation;

class SentenceValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  SentenceValidationException(String message) {
    super(message);
  }
}
