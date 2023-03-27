package com.moyu.examples.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * UserInfo
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Data
@TableName(value = "user")
public class User {

  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  @TableField(value = "login_name")
  private String loginName;

  @TableField(value = "user_name")
  private String userName;

  @TableField(value = "password")
  private String password;

  @TableField(value = "phone")
  private String phone;

  @TableField(value = "enable")
  private Integer enable;

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
