package com.moyu.framework.mybatis.mapper;

import com.moyu.framework.core.entity.Entity;
import io.mybatis.mapper.Mapper;
import java.io.Serializable;

/**
 * BaseMapper
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface BaseMapper<T extends Entity<PK>, PK extends Serializable> extends
    Mapper<T, PK> {

}
