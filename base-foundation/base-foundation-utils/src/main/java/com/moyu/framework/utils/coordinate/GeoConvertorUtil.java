package com.moyu.framework.utils.coordinate;

/**
 * 百度坐标系、 火星坐标系、 WGS84地理坐标系 相互转换工具类
 *
 * @date 2021-08-03 16:51
 */
public final class GeoConvertorUtil {

  static double x_PI = 3.14159265358979324 * 3000.0 / 180.0;
  static double PI = 3.1415926535897932384626;
  static double radius = 6378245.0;
  static double ee = 0.00669342162296594323;

  /**
   * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换 即 百度 转 谷歌、高德
   *
   * @param lon 经度
   * @param lat 纬度
   * @return {@link Point} 坐标点
   */
  public static Point bd09ToGcj02(double lon, double lat) {
    double x = lon - 0.0065;
    double y = lat - 0.006;
    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_PI);
    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_PI);
    double gcj_lon = z * Math.cos(theta);
    double gcj_lat = z * Math.sin(theta);
    return new Point(gcj_lon, gcj_lat);
  }

  /**
   * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换 即谷歌、高德 转 百度
   *
   * @param lon 经度
   * @param lat 纬度
   * @return {@link Point} 坐标点
   */
  public static Point gcj02ToBd09(double lon, double lat) {
    double z = Math.sqrt(lon * lon + lat * lat) + 0.00002 * Math.sin(lat * x_PI);
    double theta = Math.atan2(lat, lon) + 0.000003 * Math.cos(lon * x_PI);
    double bd_lon = z * Math.cos(theta) + 0.0065;
    double bd_lat = z * Math.sin(theta) + 0.006;
    return new Point(bd_lon, bd_lat);
  }


  /**
   * WGS84坐标系转火星坐标系（GCj02）
   *
   * @param lon 经度
   * @param lat 纬度
   * @return {@link Point} 坐标点
   */
  public static Point wgs84ToGcj02(double lon, double lat) {
    double dlat = transformLat(lon - 105.0, lat - 35.0);
    double dlng = transformLon(lon - 105.0, lat - 35.0);
    double rad_lat = lat / 180.0 * PI;
    double magic = Math.sin(rad_lat);
    magic = 1 - ee * magic * magic;
    double sqrt_magic = Math.sqrt(magic);
    dlat = (dlat * 180.0) / ((radius * (1 - ee)) / (magic * sqrt_magic) * PI);
    dlng = (dlng * 180.0) / (radius / sqrt_magic * Math.cos(rad_lat) * PI);
    double gcj_lon = lon + dlng;
    double gcj_lat = lat + dlat;
    return new Point(gcj_lon, gcj_lat);
  }


  /**
   * 火星坐标系（GCJ02） 转换为 WGS84坐标系
   *
   * @param lon 经度
   * @param lat 纬度
   * @return {@link Point} 坐标点
   */
  public static Point gcj02toWgs84(double lon, double lat) {
    double dlat = transformLat(lon - 105.0, lat - 35.0);
    double dlng = transformLon(lon - 105.0, lat - 35.0);
    double rad_lat = lat / 180.0 * PI;
    double magic = Math.sin(rad_lat);
    magic = 1 - ee * magic * magic;
    double sqrt_magic = Math.sqrt(magic);
    dlat = (dlat * 180.0) / ((radius * (1 - ee)) / (magic * sqrt_magic) * PI);
    dlng = (dlng * 180.0) / (radius / sqrt_magic * Math.cos(rad_lat) * PI);
    double wgs_lon = lon + dlng;
    double wgs_lat = lat + dlat;
    return new Point(wgs_lon, wgs_lat);
  }


  private static double transformLat(double lon, double lat) {
    double ret = -100.0 + 2.0 * lon + 3.0 * lat + 0.2 * lat * lat + 0.1 * lon * lat + 0.2 * Math
        .sqrt(Math.abs(lon));
    ret += (20.0 * Math.sin(6.0 * lon * PI) + 20.0 * Math.sin(2.0 * lon * PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
    ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
    return ret;
  }

  private static double transformLon(double lon, double lat) {
    double ret = 300.0 + lon + 2.0 * lat + 0.1 * lon * lon + 0.1 * lon * lat + 0.1 * Math
        .sqrt(Math.abs(lon));
    ret += (20.0 * Math.sin(6.0 * lon * PI) + 20.0 * Math.sin(2.0 * lon * PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lon * PI) + 40.0 * Math.sin(lon / 3.0 * PI)) * 2.0 / 3.0;
    ret += (150.0 * Math.sin(lon / 12.0 * PI) + 300.0 * Math.sin(lon / 30.0 * PI)) * 2.0 / 3.0;
    return ret;
  }
}
