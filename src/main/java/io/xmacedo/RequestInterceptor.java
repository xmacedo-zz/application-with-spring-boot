package io.xmacedo;

import io.xmacedo.context.Context;
import io.xmacedo.context.ContextHolder;
import io.xmacedo.exception.Exception;
import io.xmacedo.exception.ExceptionEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws java.lang.Exception {
        Context context = Context.from(request);

        ContextHolder.set(context);

        if (context.getTransactionId() == null) {
            throw new Exception(ExceptionEnum.TRANSACTION_ID_HEADER_IS_MISSING);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws java.lang.Exception {
        ContextHolder.remove();
    }

}
