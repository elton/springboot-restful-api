package com.prosight.restfulapi.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author elton
 * @version 1.0
 * @date 2020-10-16 12:42
 */
@Entity
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, length = 20, nullable = false)
  public long id;

  @NotNull
  @Column(columnDefinition = "varchar(50) comment '书名'")
  public String name;

  @NotNull
  @Column(columnDefinition = "varchar(25) comment '作者'")
  public String author;

  @Column(columnDefinition = "varchar(255) comment '描述'")
  public String description;

  @ColumnDefault("1")
  @Column(columnDefinition = "tinyint(1) comment '是否存在'")
  public Boolean status;
}
