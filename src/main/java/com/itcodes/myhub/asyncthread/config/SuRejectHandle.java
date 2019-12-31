package com.itcodes.myhub.asyncthread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName SuRejectHandle    自定义拒绝策略
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
@Component
@Slf4j
public class SuRejectHandle implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //打印丢失的任务
        log.error("线程[{}],[{}]任务丢失",Thread.currentThread().getName(),r);
    }
}
