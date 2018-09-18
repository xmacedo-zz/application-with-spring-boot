package io.xmacedo.client.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class RestExecutorAsyncConfig {

    @Value("${rest_executor.threads:50}")
    private int restExecutorThreads;

    @Bean("restExecutorThreadPool")
    public ExecutorService restExecutorThreadPool() {
        return Executors.newFixedThreadPool(restExecutorThreads);
    }

}
