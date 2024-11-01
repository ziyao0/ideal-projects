package com.ziyao.ideal.uaa.common.exception;

import com.ziyao.ideal.security.core.AuthenticationException;
import com.ziyao.ideal.web.response.MsgResp;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class AuthenticationFailureException extends AuthenticationException implements MsgResp {
    @Serial
    private static final long serialVersionUID = 2065959447074513639L;

    private final MsgResp msgResp;


    public AuthenticationFailureException(String message) {
        super(message);
        this.msgResp = MsgResp.getInstance(401, message);
    }

    public AuthenticationFailureException(int code, String message) {
        super(message);
        this.msgResp = MsgResp.getInstance(code, message);
    }

    public AuthenticationFailureException(MsgResp msgResp) {
        super(msgResp.getMessage());
        this.msgResp = msgResp;
    }

    @Override
    public Integer getStatus() {
        return msgResp.getStatus();
    }
}
