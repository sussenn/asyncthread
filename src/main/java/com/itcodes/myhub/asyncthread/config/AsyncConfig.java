package com.itcodes.myhub.asyncthread.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncConfig   异步@Async多线程配置
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
//@Configuration
//@EnableAsync
public class AsyncConfig {

    @Autowired
    private SuRejectHandle rejectHandle;

    @Bean("myAsyn1")
    public Executor myAsyncc() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心线程数
        executor.setMaxPoolSize(10);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(20);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("myAsync1-");//线程名前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//任务丢失处理策略
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    @Bean("myAsync2")
    public Executor myAsync2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);//核心线程数
        executor.setMaxPoolSize(3);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(1);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间
        executor.setThreadNamePrefix("myAsync2-");//线程名前缀
        executor.setRejectedExecutionHandler(rejectHandle);// 自定义任务丢失处理策略   该策略输出由scheduling-1打印
        //设置线程池等待所有任务都完成再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
