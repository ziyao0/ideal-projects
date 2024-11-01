package com.ziyao.ideal.web.exception;


import com.ziyao.ideal.web.response.MsgResp;

import java.io.Serial;

/**
 * @author zhangziyao
 */
public class ServiceException extends RuntimeException implements MsgResp {
    @Serial
    private static final long serialVersionUID = -3435528093859682944L;


    private final MsgResp msgResp;

    public ServiceException() {
        // TODO: 2023/9/24 业务异常
        this.msgResp = null;
    }

    public ServiceException(MsgResp MsgResp) {
        this.msgResp = MsgResp;
    }

    public ServiceException(Integer status, String message) {
        this.msgResp = MsgResp.getInstance(status, message);
    }

    @Override
    public Integer getStatus() {
        return msgResp.getStatus();
    }


    @Override
    public String getMessage() {
        return msgResp.getMessage();
    }
}
