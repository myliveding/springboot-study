package com.example.springbootstudy.controller;

import com.example.springbootstudy.common.BizException;
import com.example.springbootstudy.common.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目关闭控制器
 * @author dingzr 2023-06-12 15:26
 */
@Slf4j
@RestController
@RequestMapping("/shutdown")
public class ShutdownController implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * ApplicationContext 关闭服务
     */
    @PostMapping("/contextClose")
    public void shutdownContext(){
        ((ConfigurableApplicationContext) context).close();
    }

    /**
     * System.exit(20)
     */
    @PostMapping("/system_exit")
    public String exit() {
        System.exit(20);
        return "SUCCESS";
    }

    /**
     * 循环执行的业务
     */
    @PostMapping(value = "/lifecycle")
    public RequestResult<String> lifecycle() {
        try {
            log.info("优雅关闭，开始进入睡眠等待...");
            //throw new BizException("ddd");
            Thread.sleep(15 * 1000);
        }catch (Exception e){
            e.printStackTrace();
        //    throw new BizException("ddd");
        }

        //for (int i = 1; i <= 15; i++) {
        //    log.info("circulate {} sleep", i);
        //    try {
        //        Thread.sleep(1*1000);
        //    } catch (InterruptedException e) {
        //    }
        //    log.info("sleep {} seconds", i);
        //}
        System.err.println("-------");
        log.info("优雅关闭，睡眠结束...开始执行业务...");
        // 其他业务  数据库什么的
        return RequestResult.success("SUCCESS");
    }

}
