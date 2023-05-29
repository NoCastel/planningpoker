package com.sourcery.planningpoker.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionBuilder extends Exception {

  private final HttpStatus httpStatus;

  public ExceptionBuilder(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
