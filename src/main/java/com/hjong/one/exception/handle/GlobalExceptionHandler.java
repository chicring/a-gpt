package com.hjong.one.exception.handle;

import com.hjong.one.entity.R;
import com.hjong.one.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/11
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                       HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.fail(e.getMessage());
    }

    /**
     * 自定义的业务异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public R<Void> handleServiceException(ServiceException e){
        log.error("业务异常：{}",e.getDetailMessage());
        Integer code = e.getCode();
        return code != null ? R.fail(code, e.getMessage()) : R.fail(e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public R<Void> exceptionHandler(Exception e) {
        log.error("发生未知异常.",e);
        return R.fail(e.getMessage());
    }

    /**
     * webClint请求异常
     */
    @ExceptionHandler(value = WebClientResponseException.class)
    public R<Void> handleWebClientResponseException(WebClientResponseException e){
        HttpStatus status = (HttpStatus) e.getStatusCode();
        String res = e.getResponseBodyAsString();
        log.error("API error: {} {}", status, res);
        return R.fail(status.value(),res);
    }

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R<Void> handleConstraintViolationException(ConstraintViolationException e){
        log.error("参数异常：{}",e.getMessage());
        return R.fail(e.getMessage());
    }


}
