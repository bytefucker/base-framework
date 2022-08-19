package com.moyu.framework.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库实体基类，默认继承审计和软删除字段 <br>
 * 不需要审计和软删除功能可以直接继承Entity接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseEntity<PK extends Serializable> implements Entity<PK>,
    Auditable, LogicDelete {

  private PK id;

  private Integer deleted;

  private String createBy;

  private Date createTime;

  private String updateBy;

  private Date updateTime;


  public PK getId() {
    return id;
  }

  public void setId(PK id) {
    this.id = id;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}