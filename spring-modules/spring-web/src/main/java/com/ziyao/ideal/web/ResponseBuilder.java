package com.ziyao.ideal.web;


import com.ziyao.ideal.web.response.ResponseMetadata;
import com.ziyao.ideal.web.response.ResponseWrapper;

/**
 * @author ziyao zhang
 */
public abstract class ResponseBuilder {

    public static <T> ResponseWrapper<T> ok(T data) {
        return ResponseBuilder.ok(ResponseMetadata.SUCCESS_STATE(), ResponseMetadata.SUCCESS_MESSAGE(), data);
    }

    public static <T> ResponseWrapper<T> ok() {
        return new ResponseWrapper<>(ResponseMetadata.SUCCESS_STATE(), ResponseMetadata.SUCCESS_MESSAGE());
    }

    public static <T> ResponseWrapper<T> ok(Integer state, String message, T data) {
        return new ResponseWrapper<>(state, message, data);
    }

    public static <T> ResponseWrapper<T> failed() {
        return new ResponseWrapper<>(ResponseMetadata.FAILED_STATE(), ResponseMetadata.FAILED_MESSAGE());
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message) {
        return new ResponseWrapper<>(state, message);
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message, T data) {
        return new ResponseWrapper<>(state, message, data);
    }

    public static <T> ResponseWrapper<T> of(ResponseMetadata ResponseMetadata, String message) {
        return of(ResponseMetadata.getStatus(), message);
    }

    public static <T> ResponseWrapper<T> of(ResponseMetadata ResponseMetadata) {
        return of(ResponseMetadata.getStatus(), ResponseMetadata.getMessage());
    }

    private ResponseBuilder() {

    }
}
