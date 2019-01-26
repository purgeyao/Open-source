package com.mercy.demo.advice;

import com.mercy.demo.annotation.IgnorReponseAdvice;
import com.mercy.demo.vo.ConmmonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回包装
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-26
 */
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        // 检查注解是否存在
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnorReponseAdvice.class)){
            return false;
        }

        if (methodParameter.getMethod().isAnnotationPresent(IgnorReponseAdvice.class)){
            return false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        ConmmonResponse<Object> response = new ConmmonResponse<>(0, "");

        // o is null -> return response
        if (o == null){
            return response;
        }
        // o is instanceof ConmmonResponse-> return o
        else if (o instanceof ConmmonResponse){
            response = (ConmmonResponse<Object>) o;
        }
        // response.setData(o);
        else {
            response.setData(o);
        }

        return response;
    }
}
