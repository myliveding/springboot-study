//package com.example.springbootstudy.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.connector.Connector;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextClosedEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author dingzr 2023-06-12 17:37
// */
//@Slf4j
//@Component
//public class TomcatGracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
//
//    private volatile Connector connector;
//
//    @Override
//    public void customize(Connector connector) {
//        this.connector = connector;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
//        log.info("监听contextClosedEvent事件，开始 gracefully...");
//        this.connector.pause();
//        Executor executor = this.connector.getProtocolHandler().getExecutor();
//        if (executor instanceof ThreadPoolExecutor) {
//            try {
//                log.info("Start to shutdown tomcat thread pool.");
//                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//                threadPoolExecutor.shutdown();
//                if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
//                    log.warn("Tomcat thread pool did not shutdown gracefully within 30 seconds. ");
//                }
//            } catch (InterruptedException e) {
//                log.warn("Fail to shut down tomcat thread pool ", e);
//            }
//        }
//    }
//}
