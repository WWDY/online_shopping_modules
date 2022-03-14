package com.wwdy.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * @author wwdy
 * @date 2022/3/12 13:22
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        return GlobalThreadPoolExecutor.THREAD_POOL_EXECUTOR.getThreadPoolExecutor();
    }
}
