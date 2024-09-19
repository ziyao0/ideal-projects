package com.ziyao.ideal.gateway.filter.body;

import lombok.Data;

/**
 * @author ziyao zhang
 */
@Data
public class ReqRes {

    private String reqBody;
    private String resBody;
    /**
     * 耗时
     */
    private long time;

    public ReqRes() {
    }

    public ReqRes(String reqBody) {
        this.reqBody = reqBody;
    }

    public ReqRes(String reqBody, String resBody) {
        this.reqBody = reqBody;
        this.resBody = resBody;
    }

    public static ReqRes of(String reqBody) {
        return new ReqRes(reqBody);
    }

    public static ReqRes of(String reqBody, String resBody) {
        return new ReqRes(reqBody, resBody);
    }
}
