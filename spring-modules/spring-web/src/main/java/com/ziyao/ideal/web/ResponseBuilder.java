package com.ziyao.ideal.web;


import com.ziyao.ideal.web.response.MsgResp;
import com.ziyao.ideal.web.response.ResponseWrapper;

/**
 * @author ziyao zhang
 */
public abstract class ResponseBuilder {

    public static <T> ResponseWrapper<T> ok(T data) {
        return ResponseBuilder.ok(MsgResp.SUCCESS_MESSAGE(), data);
    }

    public static <T> ResponseWrapper<T> ok() {
        return new ResponseWrapper<>(MsgResp.SUCCESS_STATE(), MsgResp.SUCCESS_MESSAGE());
    }

    public static <T> ResponseWrapper<T> ok(String message, T data) {
        return new ResponseWrapper<>(MsgResp.SUCCESS_STATE(), message, data);
    }

    public static <T> ResponseWrapper<T> failed() {
        return new ResponseWrapper<>(MsgResp.FAILED_STATE(), MsgResp.FAILED_MESSAGE());
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message) {
        return new ResponseWrapper<>(state, message);
    }

    public static <T> ResponseWrapper<T> of(Integer state, String message, T data) {
        return new ResponseWrapper<>(state, message, data);
    }

    public static <T> ResponseWrapper<T> of(MsgResp MsgResp, String message) {
        return of(MsgResp.getStatus(), message);
    }

    public static <T> ResponseWrapper<T> of(MsgResp MsgResp) {
        return of(MsgResp.getStatus(), MsgResp.getMessage());
    }

    public static <T> ResponseWrapper<T> badRequest() {
        return ResponseBuilder.of(400, "请求参数错误，请检查输入");
    }

    public static <T> ResponseWrapper<T> unauthorized() {
        return ResponseBuilder.of(401, "当前功能需要登录后才能访问，请登录后再操作该功能");
    }

    public static <T> ResponseWrapper<T> forbidden() {
        return of(403, "您没有操作当前功能的权限，请联系管理员授权后操作");
    }

    public static <T> ResponseWrapper<T> internalServerError() {
        return of(500, "当前功能访问繁忙，请稍后再试");
    }

    private ResponseBuilder() {

    }
}
