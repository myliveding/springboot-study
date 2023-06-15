package com.example.springbootstudy.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author dingzr 2023-06-12 15:11
 */
@Slf4j
@Component
public class ApplicationDestroyListener implements DisposableBean {

    @Override
    public void destroy() {
        log.info("DisposableBean 应用正在关闭，清理相关数据");
    }

}