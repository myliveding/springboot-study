package com.example.springbootstudy.test;

import com.example.springbootstudy.config.ShutdownHook;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingzr 2023-06-12 14:35
 */
@Slf4j
public class TestExit {

    public static void main( String[] args ) {
        for (int i = 0; i < 100; i++) {
            log.info("circulate {}", i);
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
            }
            log.info("sleep {} seconds", i+1);

            if(i > 10){
                //终止退出我们正在运行的当前程序的 Java 虚拟机 (JVM)。
                log.info("terminate JVM at {}" , i);
                System.exit(20);
            }
        }
    }

}