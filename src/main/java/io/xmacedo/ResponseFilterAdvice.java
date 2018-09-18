package io.xmacedo;

import io.xmacedo.client.rest.Response;
import org.springframework.boot.actuate.health.Health;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.DispatcherType;

@ControllerAdvice
public class ResponseFilterAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof Response || body instanceof Health || ((ServletServerHttpRequest) request).getServletRequest().getDispatcherType() == DispatcherType.ERROR) {
            return body;
        }

        Response<Object> localResponse = new Response<>();
        Response.Meta    meta         = Response.Meta.fromContext("success");

        localResponse.setMeta(meta);
        localResponse.setData(body != null ? body : new Object());

        return localResponse;
    }

}
