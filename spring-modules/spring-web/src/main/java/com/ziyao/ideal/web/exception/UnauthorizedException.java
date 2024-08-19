package com.ziyao.ideal.web.exception;

import com.ziyao.ideal.web.response.ResponseDetails;
import lombok.Setter;



/**
 * @author zhangziyao
 */
@Setter
public class UnauthorizedException extends RuntimeException implements ResponseDetails {
    
    private static final long serialVersionUID = 1350454124169036151L;


    private int status;

    private String message;

    public UnauthorizedException() {
        ServiceException unauthorizedException = Exceptions.createUnauthorizedException(null);
        this.status = unauthorizedException.getStatus();
        this.message = unauthorizedException.getMessage();
    }

    public UnauthorizedException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public UnauthorizedException(String message) {
        this.status = 403;
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
