package com.prosight.restfulapi.exception;

/**
 * 资源未找到异常
 * 
 * @author Elton
 * @version 1.0
 * @date 2020-10-18 10:10
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
