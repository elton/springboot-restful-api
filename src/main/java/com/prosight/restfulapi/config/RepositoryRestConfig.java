package com.prosight.restfulapi.config;

import com.prosight.restfulapi.validator.BookValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * 配置校验规则
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 17:29
 */
@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {
  @Override
  public void configureValidatingRepositoryEventListener(
      ValidatingRepositoryEventListener validatingListener) {
    // 保存之前验证
    validatingListener.addValidator("beforeSave", new BookValidator());
    // 创建之前验证
    validatingListener.addValidator("beforeCreate", new BookValidator());
  }
}
