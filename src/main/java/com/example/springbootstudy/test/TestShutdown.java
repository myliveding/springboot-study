package com.example.springbootstudy.test;

import com.example.springbootstudy.config.ShutdownHook;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingzr 2023-06-12 14:35
 */
@Slf4j
public class TestShutdown {

    private ShutdownHook shutdownHook;

    public static void main( String[] args ) {
        TestShutdown app = new TestShutdown();
        log.info("主--Hello World!");
        app.execute();
        log.info("主--End of main()");
    }

    public TestShutdown(){
        this.shutdownHook = new ShutdownHook(Thread.currentThread());
    }

    public void execute(){
        while(!shutdownHook.shouldShutDown()){
            log.info("主--I am sleep");
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                log.info("主--execute() interrupted");
            }
            log.info("主--I am not sleep");
        }

        log.info("主--end of execute()");
    }
}