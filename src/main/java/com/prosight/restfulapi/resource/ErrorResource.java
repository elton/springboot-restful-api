package com.prosight.restfulapi.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 错误资源
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 10:32
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ErrorResource {
    private String message;
}
