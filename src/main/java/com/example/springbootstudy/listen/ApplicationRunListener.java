package com.example.springbootstudy.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author dingzr 2023-06-12 15:11
 */
@Slf4j
@Component
public class ApplicationRunListener implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("CommandLineRunner 应用启动成功，预加载相关数据");
    }

}