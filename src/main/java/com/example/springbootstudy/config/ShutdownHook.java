package com.example.springbootstudy.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dingzr 2023-06-12 14:34
 */
@Slf4j
public class ShutdownHook extends Thread {

    private Thread mainThread;

    private boolean shutDownSignalReceived;

    @Override
    public void run() {
        log.info("Shut down signal received.");
        this.shutDownSignalReceived=true;
        mainThread.interrupt();
        try {
            //当收到停止信号时，等待mainThread的执行完成
            mainThread.join();
        } catch (InterruptedException e) {
        }
        log.info("Shut down complete.");
    }

    public ShutdownHook(Thread mainThread) {
        super();
        this.mainThread = mainThread;
        this.shutDownSignalReceived = false;
        Runtime.getRuntime().addShutdownHook(this);
    }

    public boolean shouldShutDown(){
        return shutDownSignalReceived;
    }

}
