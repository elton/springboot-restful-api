package com.prosight.restfulapi.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 错误封装
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 10:38
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class InvalidErrorResource {
    private String message;
    private Object errors;
}
