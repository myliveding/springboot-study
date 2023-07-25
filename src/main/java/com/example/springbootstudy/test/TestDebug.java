package com.example.springbootstudy.test;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 测试debug的实用技巧
 * @author dingzr 2023-06-19 16:13
 */
@Slf4j
public class TestDebug {

    // 引用的文章地址
    // https://juejin.cn/post/7039307515393212429#heading-4
    // https://blog.csdn.net/zcl_love_wx/article/details/124897065
    // https://www.didispace.com/idea-tips/debug/debug-backward-jump-to-line.html

    /**
     * 一、idea debug处即兴抛异常
     */
    public static void testException(){
        log.info("debug 自定义异常 {}", "开始...");

        // 点击debug里面的throw exception 编写 new Exception("自定义异常")
        log.info("idea 自定义异常 {}", "业务逻辑处理");

        log.info("idea 自定义异常 {}", "结束...");
    }


    /**
     * 二、idea debug加筛选条件运行
     * 右键debug端点  加上条件
     */
    public static void testFilter(){
        log.info("debug 筛选条件运行 {}", "开始...");
        for (int i = 0; i < 1000; i++) {
            log.info("debug 筛选条件运行循环数 {}", i);
        }
    }

    /**
     * 三、idea debug提前结束
     * 代码运行到断点处时，在左下角的Frames里右键，先择Force Return
     * Debug 调试代码时，跟到一个无关痛痒的方法，有时候我们希望提前返回，可以使用下面的方法强制返回一个指定的值。
     */
    public static String testForceReturn(){
        log.info("debug 提前结束 {}", "开始...");
        for (int i = 0; i < 10; i++) {
            log.info("debug 提前结束循环数 {}", i);
        }
        return "操作流程结束";
    }

    /**
     * 四、idea debug断点回退
     * 阅读Spring IOC 启动流程源码时，方法调用都很深，想退回来再看看上一步做了啥，用方法回退很方便。
     * 可以选择回退到某个上层方法，也可以点击图标回退到上一步
     */
    public static int testBack(){
        log.info("debug 断点回退 {}", "开始...");
        int i = 1;

        i = i + 80;

        log.info("操作结果：{}", i);
        return i;
    }

    /**
     * 五、idea debug Java8 Stream
     */
    public static void testStream(){
        log.info("debug stream {}", "开始...");
        List<Integer> list1 = Arrays.asList(1, 5, 3, 2, 4);
        List<Integer> list2 = list1.stream()
                .filter(o -> o > 2)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list2);
    }

    /**
     * 六、idea debug Optional 调试 (断点时执行函数)
     */
    public static void testOptional(){
        log.info("debug Optional 断点时执行函数 {}", "开始...");

        List<Integer> list1 = Arrays.asList(1, 5, 3, 2, 4);
        Optional<Integer> optional = list1.stream().filter(t -> t == 3).findFirst();

        System.out.println(optional.orElseGet(null));
    }

    /**
     * 七、idea debug 多线程调试
     */
    public static void testThread(){
        log.info("debug Optional 多线程调试 {}", "开始...");

        new Thread(() -> {
            log.info("线程1执行");
        }, "线程1").start();

        new Thread(() -> {
            log.info("线程2执行");
        }, "线程2").start();

        log.info("主线程");
    }


    /**
     * 八、 Jump To Line  // TODO: 2023/6/19
     */



    public static void main(String[] args) {
        //testException();

        //testFilter();

        //System.err.println(testForceReturn());

        System.err.println(testBack());

        //testStream();

        //testOptional();

//        testThread();

    }
}
