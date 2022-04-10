package com.wwdy.front.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import result.ResultUtil;
import result.enums.ResultEnum;
import result.vo.DataError;
import result.vo.ResultVO;

import javax.lang.model.type.NullType;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author wwdy
 * @date 2022/2/21 17:10
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String domain;


    /**
     * 非预期异常的处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler({Exception.class})
    public ResultVO<NullType> handleException(Exception e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        log.error("[error] exception = {},===> request_url = {}",e,request.getRequestURL());
        e.printStackTrace();
        return ResultUtil.error(ResultEnum.UNEXPECTED_ERROR.getCode(), e.getMessage(), error);
    }

    /**
     * Http请求ContentType错误处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultVO<NullType> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        error.addError("", e.getMessage());
        log.error("ContentType error ===> request_url = {}",request.getRequestURL());
        return ResultUtil.error(ResultEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED_ERROR, error);
    }

    /**
     * Http请求MethodType错误处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO<NullType> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        error.addError("", e.getMessage());
        log.error("MethodType error ===> request_url = {} ===> MethodType = {}",request.getRequestURL(),request.getMethod());
        return ResultUtil.error(ResultEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_ERROR, error);
    }

    /**
     * HttpMessage转换异常处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO<NullType> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        log.error("HttpMessage error ===> request_url = {}",request.getRequestURL());log.error("ContentType error ===> request_url = {}",request.getRequestURL());
        return ResultUtil.error(ResultEnum.ERROR.getCode(), e.getMessage(), error);
    }

    /**
     * 缺少Get参数时异常处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<NullType> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        error.addError(e.getParameterName(), String.valueOf(e.getMessage()));
        log.error("MissingGetParams error ===> request_url = {}",request.getRequestURL());log.error("ContentType error ===> request_url = {}",request.getRequestURL());
        return ResultUtil.error(ResultEnum.MISSING_SERVLET_REQUEST_PARAMETER_ERROR, error);
    }

    /**
     * 缺少Get参数时异常处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO<NullType> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        error.addError(e.getName(), String.valueOf(e.getMessage()));
        log.error("MissingGetParams error ===> request_url = {}",request.getRequestURL());log.error("ContentType error ===> request_url = {}",request.getRequestURL());
        return ResultUtil.error(ResultEnum.METHOD_ARGUMENT_TYPE_MISMATCH_ERROR, error);
    }

    /**
     * 参数校验失败的异常处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResultVO<NullType> handleBindingException(Exception e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());

        String message = null;
        if (e instanceof BindException) {
            BindingResult bindResult = ((BindException) e).getBindingResult();
            for (FieldError fieldError : bindResult.getFieldErrors()) {
                error.addError(fieldError.getField(), fieldError.getDefaultMessage());
            }
            if (Optional.ofNullable(bindResult.getFieldError()).isPresent()) {
                message = bindResult.getFieldError().getDefaultMessage();
            }
        }
        log.warn("请求参数非预期异常: domain = {}, {} - {}, error = {}", domain, request.getMethod(), request.getRequestURI(), error);
        return ResultUtil.error(ResultEnum.PARAM_VALIDATED_UN_PASS.getCode(), message, error);
    }




    /**
     * SQL异常错误处理
     *
     * @param e       异常信息
     * @param request 请求
     * @return ResultVO
     */
    @ExceptionHandler(SQLException.class)
    public ResultVO<NullType> handleSqlException(SQLException e, HttpServletRequest request) {
        DataError error = new DataError();
        error.setDomain(domain);
        error.setException(e.getClass().getName());
        log.error("SQL error ===> request_url = {}",request.getRequestURL());log.error("ContentType error ===> request_url = {}",request.getRequestURL());
        return ResultUtil.error(e.getMessage(), error);
    }
}
