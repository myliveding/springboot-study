package com.example.springbootstudy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dingzr 2023-07-25
 */
public class Test {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
//    list.add("大马村");
//    list.add("牛郎");
//    list.add("仙女");
//    list.add("黄河");
//    list.add("耶鲁藏布江");


    List<String> a = list.stream().limit(3).collect(Collectors.toList());
    System.err.println(String.join(",", a).equals(""));


    System.err.println(list.stream().limit(3).collect(Collectors.joining(",")));
  }

}