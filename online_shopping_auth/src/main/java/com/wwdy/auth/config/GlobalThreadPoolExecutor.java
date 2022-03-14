package com.wwdy.auth.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.Getter;

import java.util.concurrent.*;

/**
 * @author wwdy
 * @date 2022/3/12 13:09
 */
@Getter
public enum GlobalThreadPoolExecutor {

    /**
     * ThreadPoolExecutor
     */
    THREAD_POOL_EXECUTOR;

    private final ThreadPoolExecutor threadPoolExecutor;

    GlobalThreadPoolExecutor(){
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true).setNamePrefix("global-thread-poll-").build();
        this.threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                3600L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(maximumPoolSize * 2),
                threadFactory
        );
    }
}
