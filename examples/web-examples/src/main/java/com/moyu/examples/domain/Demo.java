package com.moyu.examples.domain;

import io.mybatis.provider.Entity;
import io.mybatis.provider.Entity.Column;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity.Table(value = "demo")
public class Demo implements Serializable {

  /**
   *
   */
  @Column(id = true, value = "id", insertable = false)
  private Long id;

  /**
   *
   */
  @Column(value = "name")
  private String name;

  /**
   *
   */
  @Column(value = "create_by", updatable = false)
  private String createBy;

  /**
   *
   */
  @Column(value = "create_time", updatable = false)
  private Date createTime;

  /**
   *
   */
  @Column(value = "update_by", insertable = false)
  private String updateBy;

  /**
   *
   */
  @Column(value = "update_time", insertable = false)
  private Date updateTime;
}