package com.moyu.examples.domain;

import io.mybatis.provider.Entity;
import io.mybatis.provider.Entity.Column;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName demo
 */
@Data
@Entity.Table(value = "demo")
public class Demo implements Serializable {

  /**
   *
   */
  @Column(id = true, value = "id")
  private Long id;

  /**
   *
   */
  @Column(value = "name")
  private String name;

  /**
   *
   */
  @Column(value = "create_by")
  private String createBy;

  /**
   *
   */
  @Column(value = "create_time")
  private Date createTime;

  /**
   *
   */
  @Column(value = "update_by")
  private String updateBy;

  /**
   *
   */
  @Column(value = "update_time")
  private Date updateTime;
}