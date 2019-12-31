package com.itcodes.myhub.asyncthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName AsyncThreadApplication    定时任务和异步注解 的线程池配置
 * [鉴于阿里巴巴规范不允许使用Executors去创建线程池]
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
//@EnableScheduling     //如果是AsyncConfig配置 配合定时任务测试,则需要开启此注解
@SpringBootApplication
public class AsyncThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncThreadApplication.class,args);
    }
}
