package com.ziyao.ideal.web.exception;


import com.ziyao.ideal.web.response.ResponseDetails;

/**
 * @author ziyao zhang
 */
public abstract class Exceptions {

    public Exceptions() {
    }


    /**
     * 通用: 请求成功.
     */
    public static final ResponseDetails SUCCESS = ResponseDetails.getInstance(ResponseDetails.SUCCESS_STATE(), "请求成功");

    /**
     * 创建非法访问
     *
     * @param message 异常信息
     * @return {@link ServiceException}
     */
    public static ServiceException createIllegalArgumentException(String message) {
        return new ServiceException(400, "参数错误：" + message);
    }

    /**
     * 登录失败
     */
    public static ServiceException createLoginFailedException(String message) {
        return new ServiceException(4011, "登录失败：" + message);
    }

    /**
     * 资源不存在
     */
    public static ServiceException createNotFoundException(String message) {
        return new ServiceException(404, "资源不存在：" + message);
    }

    /**
     * 请求超时
     */

    public static ServiceException createRequestTimeoutException(String message) {
        return new ServiceException(408, "请求超时：" + message);
    }

    /**
     * 通用: 服务器内部错误.
     */
    public static ServiceException createInternalServerErrorException(String message) {
        return new ServiceException(500, "服务器内部错误：" + message);
    }

    /**
     * 通用: 用户未登录.
     */
    public static final ResponseDetails USER_NOT_LOGIN = ResponseDetails.getInstance(530, "用户未登录");

    /**
     * 通用: 新增失败.
     */
    public static final ResponseDetails INSERT_FAIL = ResponseDetails.getInstance(10000, "新增失败");

    /**
     * 通用: 更新失败.
     */
    public static final ResponseDetails UPDATE_FAIL = ResponseDetails.getInstance(10001, "更新失败");

    /**
     * 通用: 删除失败.
     */
    public static final ResponseDetails DELETE_FAIL = ResponseDetails.getInstance(10002, "删除失败");

    /**
     * 通用: 批量操作执行失败.
     */
    public static final ResponseDetails BATCH_ERROR = ResponseDetails.getInstance(10003, "批量操作执行失败");
    /**
     * 通用: 解析token算法不匹配.
     */
    public static final ResponseDetails ALGORITHM_MISMATCH = ResponseDetails.getInstance(401, "解析Token算法不匹配");
    /**
     * 通用: 校验签名失败.
     */
    public static final ResponseDetails SIGNATURE_VERIFICATION = ResponseDetails.getInstance(401, "校验签名异常");
    /**
     * 通用: token过期.
     */
    public static final ResponseDetails TOKEN_EXPIRED = ResponseDetails.getInstance(401, "Token过期");
    /**
     * 通用: token非法.
     */
    public static final ResponseDetails JWT_DECODE = ResponseDetails.getInstance(401, "Token非法");
    /**
     * 通用: 操作token异常.
     */
    public static final ResponseDetails TOKEN_ERROR = ResponseDetails.getInstance(401, "解析Token异常");


    /**
     * 创建请求未认证异常
     *
     * @param message 异常信息
     * @return {@link ServiceException}
     */
    public static ServiceException createUnauthorizedException(String message) {
        return new ServiceException(401, message);
    }

    /**
     * 创建越权访问异常
     *
     * @param message 异常信息
     * @return {@link ServiceException}
     */
    public static ServiceException createForbiddenException(String message) {
        return new ServiceException(403, message);
    }

    /**
     * 创建非法访问
     *
     * @param message 异常信息
     * @return {@link ServiceException}
     */
    public static ServiceException createIllegalAccessException(String message) {
        return new ServiceException(403, message);
    }
}
