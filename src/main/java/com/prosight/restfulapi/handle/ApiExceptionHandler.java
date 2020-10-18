package com.prosight.restfulapi.handle;

import com.prosight.restfulapi.exception.InvalidRequestException;
import com.prosight.restfulapi.exception.ResourceNotFoundException;
import com.prosight.restfulapi.resource.ErrorResource;
import com.prosight.restfulapi.resource.FieldResource;
import com.prosight.restfulapi.resource.InvalidErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 对异常进行拦截然后封装响应体
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 10:47
 */
@RestControllerAdvice
public class ApiExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(ResourceNotFoundException.class)
  public HttpEntity<?> handleNotFound(ResourceNotFoundException e) {
    ErrorResource errorResource = new ErrorResource(e.getMessage());
    logger.error(errorResource.toString());
    return new ResponseEntity<>(errorResource, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public HttpEntity<?> handleInvalidRequest(InvalidRequestException e) {
    logger.error(e.getMessage());
    Errors errors = e.getErrors();
    List<FieldResource> fieldResources = new ArrayList<>();
    List<FieldError> fieldErrors = errors.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      fieldResources.add(
          new FieldResource(
              fieldError.getObjectName(),
              fieldError.getField(),
              fieldError.getCode(),
              fieldError.getDefaultMessage()));
    }
    InvalidErrorResource invalidErrorResource =
        new InvalidErrorResource(e.getMessage(), fieldResources);
    logger.error(invalidErrorResource.toString());
    return new ResponseEntity<>(invalidErrorResource, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RepositoryConstraintViolationException.class)
  public HttpEntity<?> handleRepositoryConstraintViolationException(RepositoryConstraintViolationException e) {
    logger.error(e.getMessage());
    Errors errors = e.getErrors();
    List<FieldResource> fieldResources = new ArrayList<>();
    List<FieldError> fieldErrors = errors.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      fieldResources.add(
              new FieldResource(
                      fieldError.getObjectName(),
                      fieldError.getField(),
                      fieldError.getCode(),
                      fieldError.getDefaultMessage()));
    }
    InvalidErrorResource invalidErrorResource =
            new InvalidErrorResource(e.getMessage(), fieldResources);
    logger.error(invalidErrorResource.toString());
    return new ResponseEntity<>(invalidErrorResource, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public HttpEntity<?> handleException(Exception e) {
    logger.error(e.getMessage());
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
