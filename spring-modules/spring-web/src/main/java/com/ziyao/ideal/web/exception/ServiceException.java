package com.ziyao.ideal.web.exception;


import com.ziyao.ideal.web.response.ResponseDetails;

import java.io.Serial;

/**
 * @author zhangziyao
 */
public class ServiceException extends RuntimeException implements ResponseDetails {
    @Serial
    private static final long serialVersionUID = -3435528093859682944L;


    private final ResponseDetails responseDetails;

    public ServiceException() {
        // TODO: 2023/9/24 业务异常
        this.responseDetails = null;
    }

    public ServiceException(ResponseDetails ResponseDetails) {
        this.responseDetails = ResponseDetails;
    }

    public ServiceException(Integer status, String message) {
        this.responseDetails = ResponseDetails.getInstance(status, message);
    }

    @Override
    public Integer getStatus() {
        return responseDetails.getStatus();
    }


    @Override
    public String getMessage() {
        return responseDetails.getMessage();
    }
}
