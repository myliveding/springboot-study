package com.example.springbootstudy;

//import com.example.springbootstudy.config.TomcatGracefulShutdown;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import com.alibaba.ttl.threadpool.TtlExecutors;


import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dingzr
 */
@SpringBootApplication
public class SpringbootStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyApplication.class, args);
    }


    //@Resource
    //private TomcatGracefulShutdown gracefulShutdownTomcat;

    //@Bean
    //public ServletWebServerFactory servletContainer() {
    //    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    //    tomcat.addConnectorCustomizers(gracefulShutdownTomcat);
    //    return tomcat;
    //}

    @Value("${thread-pool.core-size}")
    private Integer coreSize;

    @Value("${thread-pool.maximum-size}")
    private Integer maximumSize;

    @Value("${thread-pool.queue-capacity}")
    private Integer queueCapacity;

    @Value("${thread-pool.keep-alive-seconds}")
    private Integer keepAliveSeconds;

    @Bean("globalExecutorService")
    public ExecutorService threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, maximumSize, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity), new ThreadPoolExecutor.AbortPolicy());
        return TtlExecutors.getTtlExecutorService(executor);
    }

}
