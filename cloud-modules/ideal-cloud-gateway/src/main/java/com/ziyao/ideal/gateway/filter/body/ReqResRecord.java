package com.ziyao.ideal.gateway.filter.body;

import lombok.Data;

/**
 * @author ziyao zhang
 */
@Data
public class ReqResRecord {

    private String reqContent;
    private String resContent;

    public ReqResRecord(String reqContent) {
        this.reqContent = reqContent;
    }

    public ReqResRecord(String reqContent, String resContent) {
        this.reqContent = reqContent;
        this.resContent = resContent;
    }

    public static ReqResRecord of(String reqContent) {
        return new ReqResRecord(reqContent);
    }

    public static ReqResRecord of(String reqContent, String resContent) {
        return new ReqResRecord(reqContent, resContent);
    }
}
