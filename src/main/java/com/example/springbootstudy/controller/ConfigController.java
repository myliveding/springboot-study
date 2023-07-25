package com.example.springbootstudy.controller;

import com.example.springbootstudy.common.RequestResult;
import com.example.springbootstudy.properties.ConfigListen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 配置控制器
 * @author dingzr 2023-06-12 15:26
 */
@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Resource
    private ConfigListen configListen;

    /**
     * 循环执行的业务
     */
    @PostMapping(value = "/test")
    public RequestResult<String> lifecycle() {
        return RequestResult.success(configListen.getName() + "\\n" +
                configListen.getNameList() + "\\n" +
                configListen.getNameIdList() + "\\n" +
                configListen.getNameSet());
    }

}
