package com.modefinsever.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);      // ✅ minimum threads
        executor.setMaxPoolSize(30);       // ✅ maximum threads
        executor.setQueueCapacity(1000);   // ✅ backlog capacity
        executor.setThreadNamePrefix("AsyncEmail-");
        executor.initialize();
        return executor;
    }
}
