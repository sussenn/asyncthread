package com.itcodes.myhub.asyncthread.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SuJob     定时任务多线程测试
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
@Component
@Slf4j
public class SuJob {

    //@Async("myAsync2")
    @Async
    @Scheduled(fixedDelay = 2000)
    public void job00(){
        log.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务00启动..." + Thread.currentThread().getName());
    }

    //@Async("myAsync2")
    @Async
    @Scheduled(fixedDelay = 2000)
    public void job01(){
        log.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务01启动..." + Thread.currentThread().getName());
    }

    //@Async("myAsync2")
    @Async
    @Scheduled(fixedDelay = 2000)
    public void job02(){
        log.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务02启动..." + Thread.currentThread().getName());
    }

    //@Async("myAsync2")
    @Async
    @Scheduled(fixedDelay = 2000)
    public void job03(){
        log.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务03启动..." + Thread.currentThread().getName());
    }

    //@Async("myAsync2")
    @Async
    @Scheduled(fixedDelay = 2000)
    public void job04(){
        log.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务04启动..." + Thread.currentThread().getName());
    }
}
