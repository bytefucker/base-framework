package com.moyu.framework.mybatis.mapper;

import com.moyu.framework.mybatis.model.BaseEntity;
import io.mybatis.mapper.Mapper;
import java.io.Serializable;

/**
 * BaseMapper
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface BaseMapper<T extends BaseEntity<PK>, PK extends Serializable> extends
    Mapper<T, PK> {

}
