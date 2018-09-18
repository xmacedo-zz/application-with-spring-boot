package io.xmacedo.client.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;


@Component
public class RestExecutor {

    protected static final RestTemplate REST_TEMPLATE = new RestTemplate() {
        {
            this.setErrorHandler(new ResponseErrorHandler() {

                @Override
                public boolean hasError(ClientHttpResponse response) {
                    return false;
                }

                @Override
                public void handleError(ClientHttpResponse response) {

                }

            });
        }
    };

    @Autowired
    @Qualifier("restExecutorThreadPool")
    private ExecutorService restExecutorThreadPool;

    public <T> ResponseEntity<T> execute(RestTemplate restTemplate, Request<?> request, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(request.getUrl(), request.getMethod(), new HttpEntity<>(request.getBody(), request.getHeaders()), responseType, request.getUriVariables());
    }

    public <T> ResponseEntity<T> execute(RestTemplate restTemplate, Request<?> request, Class<T> responseType) {
        return restTemplate.exchange(request.getUrl(), request.getMethod(), new HttpEntity<>(request.getBody(), request.getHeaders()), responseType, request.getUriVariables());
    }

    public <T> ResponseEntity<T> execute(Request<?> request, ParameterizedTypeReference<T> responseType) {
        return this.execute(REST_TEMPLATE, request, responseType);
    }

    public <T> ResponseEntity<T> execute(Request<?> request, Class<T> responseType) {
        return this.execute(REST_TEMPLATE, request, responseType);
    }

    public <T> Async executeAsync(RestTemplate restTemplate, Request<?> request, ParameterizedTypeReference<T> responseType, Callback<T> callback) {
        return this.submit(() -> this.execute(restTemplate, request, responseType), callback);
    }

    public <T> Async executeAsync(RestTemplate restTemplate, Request<?> request, Class<T> responseType, Callback<T> callback) {
        return this.submit(() -> this.execute(restTemplate, request, responseType), callback);
    }

    public <T> Async executeAsync(Request<?> request, ParameterizedTypeReference<T> responseType, Callback<T> callback) {
        return this.executeAsync(REST_TEMPLATE, request, responseType, callback);
    }

    public <T> Async executeAsync(Request<?> request, Class<T> responseType, Callback<T> callback) {
        return this.executeAsync(REST_TEMPLATE, request, responseType, callback);
    }

    public <T> Async submit(Callable<ResponseEntity<T>> callable, Callback<T> callback) {
        Async async = new Async();

        this.restExecutorThreadPool.submit(() -> {
            ResponseEntity<T> call      = null;
            Exception         exception = null;

            try {
                call = callable.call();

                callback.onComplete(call);
            } catch (Exception e) {
                exception = e;
                callback.onException(e);
            } finally {
                try {
                    callback.finish(call, exception);
                } finally {
                    async.semaphore.release();
                }
            }
        });

        return async;
    }
    public interface Callback<T> {

        void onException(Exception exception);

        void onComplete(ResponseEntity<T> response);

        void finish(ResponseEntity<T> response, Exception e);

    }

    public static final class Async {

        private Semaphore semaphore = new Semaphore(0);

        public void await() {
            semaphore.acquireUninterruptibly();
        }

    }

}
