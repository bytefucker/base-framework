package com.moyu.examples.mapper;

import com.moyu.examples.domain.Demo;
import io.mybatis.mapper.Mapper;

/**
 * @author yihongzhi
 * @description 针对表【demo】的数据库操作Mapper
 * @createDate 2022-08-18 17:10:54
 * @Entity com.moyu.examples.domain.Demo
 */
public interface DemoMapper extends Mapper<Demo, Long> {

}
