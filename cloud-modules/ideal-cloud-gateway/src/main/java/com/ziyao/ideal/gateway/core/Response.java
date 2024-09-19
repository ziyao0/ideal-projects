package com.ziyao.ideal.gateway.core;

import java.io.Serializable;

/**
 * @author zhangziyao
 */
public interface Response extends Serializable {

    /**
     * 用于返回成功的响应状态
     *
     * @return <code>0</code> 表示成功
     */
    static int SUCCESS_STATE() {
        return 0;
    }

    /**
     * 用于返回成功的响应消息
     *
     * @return <code>SUCCESS</code> 表示成功
     */
    static String SUCCESS_MESSAGE() {
        return "SUCCESS";
    }

    /**
     * 用于返回失败的响应状态
     *
     * @return 除<code>0</code> 表示失败
     */
    static int FAILED_STATE() {
        return -1;
    }

    /**
     * 用于返回失败的响应消息
     *
     * @return <code>FAILED</code> 表示失败
     */
    static String FAILED_MESSAGE() {
        return "FAILED";
    }

    /**
     * 用于返回当前请求的状态码
     *
     * @return <code>0</code> 表示成功
     */
    Integer status();

    /**
     * 用于返回当前请求的消息内容
     *
     * @return <code>SUCCESS</code> 表示成功，其他表示请求失败
     */
    String message();


    static Response of(Integer status, String message) {
        return new Instance(status, message);
    }

    record Instance(Integer status, String message) implements Response {

    }
}