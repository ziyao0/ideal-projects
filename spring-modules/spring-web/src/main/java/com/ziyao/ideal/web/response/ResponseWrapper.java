package com.ziyao.ideal.web.response;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

/**
 * 数据响应
 *
 * @author ziyao zhang
 */
public class ResponseWrapper<T> implements MsgResp {

    @Serial
    private static final long serialVersionUID = 7273085408208781818L;
    private static final Logger log = LoggerFactory.getLogger(ResponseWrapper.class);

    private Integer state;

    private String message;
    @Getter
    private transient T data;

    @Override
    public Integer getStatus() {
        return this.state;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ResponseWrapper() {
    }

    public ResponseWrapper(MsgResp msgResp) {
        Checker.checked(msgResp);
        this.state = msgResp.getStatus();
        this.message = msgResp.getMessage();
    }

    public ResponseWrapper(MsgResp msgResp, T data) {
        Checker.checked(msgResp, data);
        this.state = msgResp.getStatus();
        this.message = msgResp.getMessage();
        this.data = data;
    }

    public ResponseWrapper(Integer state, String message) {
        Checker.checked(state, message);
        this.state = state;
        this.message = message;

    }

    public ResponseWrapper(Integer state, String message, T data) {
        Checker.checked(state, message, data);
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public abstract static class Checker {

        private Checker() {
        }

        public static void checked(Integer state) {
            if (null == state) {
                throw new IllegalArgumentException("state cannot be empty.");
            }
        }

        public static void checked(Integer state, String message) {

            checked(state);

            if (null == message) {
                throw new IllegalArgumentException("message cannot be empty.");
            }
        }

        public static void checked(Integer state, String message, Object data) {

            checked(state, message);

            if (null == data) {
                log.debug("response data cannot be empty.");
            }
        }

        public static void checked(MsgResp msgResp) {
            checked(msgResp.getStatus(), msgResp.getMessage());
        }

        public static void checked(MsgResp msgResp, Object data) {
            checked(msgResp.getStatus(), msgResp.getMessage(), data);
        }
    }
}
