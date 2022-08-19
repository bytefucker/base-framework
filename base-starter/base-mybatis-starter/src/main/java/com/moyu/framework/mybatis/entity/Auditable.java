package com.moyu.framework.mybatis.entity;

import java.util.Date;

/**
 * Auditable
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Auditable {

  String getCreateBy();

  void setCreateBy(String createBy);

  Date getCreateTime();

  void setCreateTime(Date createTime);

  String getUpdateBy();

  void setUpdateBy(String updateBy);

  Date getUpdateTime();

  void setUpdateTime(Date updateTime);
}
