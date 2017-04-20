package com.tikal.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by pavel_sopher on 05/04/2017.
 */

@Configuration
public class AppConfiguration {

    @Bean
    @Qualifier("threadPoolTaskExecutorTest")
    @Scope(value = "singleton")
    public ThreadPoolTaskExecutor threadPoolTaskExecutorTest() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(500);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("Account-");
        executor.initialize();
        return executor;
    }
}
