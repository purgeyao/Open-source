package com.mercy.demo.advice;

import com.mercy.demo.exception.TestException;
import com.mercy.demo.vo.ConmmonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-26
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(TestException.class)
    public ConmmonResponse<Object> handleTestException(HttpServletRequest request, TestException e){
        ConmmonResponse<Object> response = new ConmmonResponse<>(-1, "test exception");
        response.setData(e.getMessage());
        return response;
    }
}
