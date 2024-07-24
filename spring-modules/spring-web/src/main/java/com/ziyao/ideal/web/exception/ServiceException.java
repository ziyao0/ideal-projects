package com.ziyao.ideal.web.exception;


import com.ziyao.ideal.web.response.ResponseMetadata;

import java.io.Serial;

/**
 * @author zhangziyao
 */
public class ServiceException extends RuntimeException implements ResponseMetadata {
    @Serial
    private static final long serialVersionUID = -3435528093859682944L;


    private final ResponseMetadata responseMetadata;

    public ServiceException() {
        // TODO: 2023/9/24 业务异常
        this.responseMetadata = null;
    }

    public ServiceException(ResponseMetadata ResponseMetadata) {
        this.responseMetadata = ResponseMetadata;
    }

    public ServiceException(Integer status, String message) {
        this.responseMetadata = ResponseMetadata.getInstance(status, message);
    }

    @Override
    public Integer getStatus() {
        return responseMetadata.getStatus();
    }


    @Override
    public String getMessage() {
        return responseMetadata.getMessage();
    }
}
