package com.moyu.examples.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Role
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Data
@TableName(value = "role")
public class Role {

  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  @TableField(value = "name")
  private String name;

  @TableField(value = "code")
  private String code;

  @TableField(value = "deleted")
  private Integer deleted;

  @TableField(value = "create_time")
  private LocalDateTime createTime;

  @TableField(value = "create_by")
  private String createBy;

  @TableField(value = "update_time")
  private LocalDateTime updateTime;

  @TableField(value = "update_by")
  private String updateBy;
}
