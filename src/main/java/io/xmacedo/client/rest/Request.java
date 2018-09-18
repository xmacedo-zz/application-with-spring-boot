package io.xmacedo.client.rest;

import io.xmacedo.context.Context;
import io.xmacedo.context.ContextHolder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Request<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = -3523540730771372696L;

    private HttpMethod          method;
    private String              url;
    private Map<String, Object> uriVariables;
    private T                   body;
    private HttpHeaders         headers;

    {
        this.uriVariables = new LinkedHashMap<>();
        this.headers = new HttpHeaders();

        Context context = ContextHolder.get();

        if (context != null) {
            this.setHeader("transactionId",context.getTransactionId());
        }
    }

    public Request(HttpMethod method, String url, T body) {
        this.method = method;
        this.url = url;
        this.body = body;
    }
    public Request(HttpMethod method, String url, String urencoded) {
        this.method = method;
        this.url = url;
        this.body = (T) urencoded;
    }

    public Request(HttpMethod method, String url) {
        this.method = method;
        this.url = url;
    }

    public Request() {

    }

    public void setHeader(String headerName,String headerValue){
        this.headers.add(headerName, headerValue);
    }
    public void setType(String type) {
        this.headers.add("Content-Type", type);
    }
    public void setLength(String length) {
        this.headers.add("Content-Length", length);
    }



    @Override
    public Request<T> clone() {
        Request<T> request = new Request<>();

        request.method = method;
        request.url = url;

        if (uriVariables != null) {
            request.uriVariables.putAll(uriVariables);
        }

        if (body != null) {
            try {
                request.body = (T) body.getClass().getMethod("clone").invoke(body);
            } catch (Exception e) {
                request.body = body;
            }
        }

        if (headers != null) {
            request.headers.putAll(headers);
        }

        return request;
    }

}
