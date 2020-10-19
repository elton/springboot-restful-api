package com.prosight.restfulapi.validator;

import com.prosight.restfulapi.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Book 校验类 （使用Spring Data Rest中用到）
 * @author elton
 * @version 1.0
 * @date 2020-10-18 17:17
 */
@Component
public class BookValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Book.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotNull","书名不能为空");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotNull","作者不能为空");
  }
}
