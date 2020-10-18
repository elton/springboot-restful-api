package com.prosight.restfulapi.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

/**
 * 请求参数异常
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 10:14
 */
public class InvalidRequestException extends RuntimeException {
  @Getter private Errors errors;

  public InvalidRequestException(String message, Errors errors) {
    super(message);
    this.errors = errors;
  }

  public InvalidRequestException(Errors errors) {
    this.errors = errors;
  }
}
