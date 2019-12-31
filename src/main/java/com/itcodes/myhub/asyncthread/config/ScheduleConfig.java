package com.itcodes.myhub.asyncthread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * @ClassName ScheduleConfig    定时任务多线程配置
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class ScheduleConfig implements SchedulingConfigurer, AsyncConfigurer {

    @Autowired
    private SuRejectHandle rejectHandle;

    /**
     * 异步线程池设置
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);//核心线程数
        executor.setMaxPoolSize(3);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(1);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("mySched-");//线程名前缀
        executor.setRejectedExecutionHandler(rejectHandle);//自定义任务丢失处理策略
        //设置线程池等待所有任务都完成再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池等待超时,否则强制关闭
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (e, method, objects) -> {
            log.error("异步任务异常:", e);
            log.error("异常所在方法[{}]", method);
        };
    }

    /**
     * 异步任务设置
     *
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }

    /**
     * 拒绝策略输出线程的设置
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("FailTask-");
        scheduler.setAwaitTerminationSeconds(20);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
