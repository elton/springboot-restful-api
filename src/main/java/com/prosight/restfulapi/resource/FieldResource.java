package com.prosight.restfulapi.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 字段错误
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 10:35
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class FieldResource {
    /**
     * 实体对象
     */
    private String resource;

    /**
     * 字段
     */
    private String field;

    /**
     * 代码
     */
    private String code;

    /**
     * 其他信息
     */
    private String message;
}
