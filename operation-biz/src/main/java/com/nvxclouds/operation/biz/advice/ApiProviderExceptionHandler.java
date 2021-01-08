package com.nvxclouds.operation.biz.advice;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/20 15:41
 * @Description: extends GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class ApiProviderExceptionHandler {

    /**
     * 自定义异常拦截处理
     * @param exception
     * @return
     */
    @ExceptionHandler({ApiProviderException.class})
    public BaseResult<Object> handleException(ApiProviderException exception) {
        log.info("======>>>>>>进入自定义异常拦截<<<<<<======");
        exception.printStackTrace();
        return response(exception.getExceptionEnum());
    }

    /**
     * 全局参数校验异常拦截处理------>@RequestBody
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult<Object> validationBodyException(MethodArgumentNotValidException exception){
        log.info("======>>>>>>进入@RequestBody参数异常拦截<<<<<<======");
        exception.printStackTrace();
        BindingResult bindingResult = exception.getBindingResult();
        return responseValidate(5003,getBindingResult(bindingResult));
    }


    /**
     * 自定义参数校验异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseResult<Object> validationBodyException(BindException exception){
        log.info("======>>>>>>进入自定义异常拦截<<<<<<======");
        exception.printStackTrace();
        BindingResult bindingResult = exception.getBindingResult();
        return responseValidate(5003,getBindingResult(bindingResult));
    }

    /**
     * 针对Controller单个参数定义校验------>@NotBalnk()
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResult<Object> handleServiceException(ConstraintViolationException e) {
        log.info("======>>>>>>进入@NotBalnk单个校验参数异常拦截<<<<<<======");
        e.printStackTrace();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        log.info("Controller单个参数异常处理======>>>>>>msg=" + message);
        return  responseValidate(5003,message);
    }

    /**
     *  针对Controller中@RequestParam校验异常处理------>@RequestParam
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResult<Object> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("======>>>>>>进入@RequestParam参数异常拦截<<<<<<======");
        e.printStackTrace();
        MissingServletRequestParameterException ex = (MissingServletRequestParameterException)e;
        return  responseValidate(5003,ex.getParameterName() + "不能为空！");
    }

    /**
     *  针对Controller中请求方式不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResult<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.info("======>>>>>>请求方式不匹配<<<<<<======");
        e.printStackTrace();
        return  responseValidate(5003,"不能用" + request.getMethod() + "方式请求，请检查接口具体请求方式post?get?put?delete? or others");
    }
    /**
     *  针对Controller中请求没带token
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public BaseResult<Object> httpRequestMethodNotSupportedException(MissingRequestHeaderException e, HttpServletRequest request) {
        log.info("======>>>>>>没有携带token<<<<<<======");
        e.printStackTrace();
        return  responseValidate(5003,"无效token！");
    }







    private BaseResult<Object> response(ApiProviderExceptionEnum exceptionEnum) {
        return  responseValidate(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }

    private BaseResult<Object> responseValidate(Integer code, String msg) {
        return BaseResult.builder().code(code).msg(msg).build();
    }

    private String getBindingResult(BindingResult bindingResult){
        List<ObjectError> list = bindingResult.getAllErrors();
        return CollectionUtils.isEmpty(list) ? "" : list.get(0).getDefaultMessage().toString();
    }
}
