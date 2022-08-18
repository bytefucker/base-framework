package com.moyu.framework.utils.coordinate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 坐标点
 *
 * @date 2021-08-03 16:49
 */
@Data
@AllArgsConstructor
@ToString
public class Point {

  /**
   * 经度
   */
  private double longitude;

  /**
   * 纬度
   */
  private double latitude;

}
