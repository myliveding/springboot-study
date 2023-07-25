package com.example.springbootstudy.util;

import java.math.BigDecimal;

/**
 * @author dingzr 2023-07-14
 */
public class BigDecimalUtil {

  /**
   * 测试 BigDecimal 的尾数0去除  &  转换成字符串
   * 对于超大的数值得尾数去0  进行toPlainString可以避免转换成科学计数法
   */
  public static void plainString(){
    BigDecimal a = BigDecimal.valueOf(223313222747774646464664.60);
    System.err.println(a.stripTrailingZeros());
    System.err.println(a.stripTrailingZeros().toPlainString());
  }

  public static void main(String[] args) {
    plainString();
  }


}