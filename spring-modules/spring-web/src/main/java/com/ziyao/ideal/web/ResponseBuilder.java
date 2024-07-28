package com.ziyao.ideal.web;


import com.ziyao.ideal.web.response.ResponseDetails;
import com.ziyao.ideal.web.response.ResponseWrapper;

/**
 * @author ziyao zhang
 */
public abstract class ResponseBuilder {

    public static <T> ResponseWrapper<T> ok(T data) {
        return ResponseBuilder.ok(ResponseDetails.SUCCESS_STATE(), ResponseDetails.SUCCESS_MESSAGE(), data);
    }

    public static <T> ResponseWrapper<T> ok() {
        return new ResponseWrapper<>(ResponseDetails.SUCCESS_STATE(), ResponseDetails.SUCCESS_MESSAGE());
    }

    public static <T> ResponseWrapper<T> ok(Integer state, String message, T data) {
        return new ResponseWrapper<>(state, message, data);
    }

    public static <T> ResponseWrapper<T> failed() {
        return new ResponseWrapper<>(ResponseDetails.FAILED_STATE(), ResponseDetails.FAILED_MESSAGE());
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message) {
        return new ResponseWrapper<>(state, message);
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message, T data) {
        return new ResponseWrapper<>(state, message, data);
    }

    public static <T> ResponseWrapper<T> of(ResponseDetails ResponseDetails, String message) {
        return of(ResponseDetails.getStatus(), message);
    }

    public static <T> ResponseWrapper<T> of(ResponseDetails ResponseDetails) {
        return of(ResponseDetails.getStatus(), ResponseDetails.getMessage());
    }

    private ResponseBuilder() {

    }
}
