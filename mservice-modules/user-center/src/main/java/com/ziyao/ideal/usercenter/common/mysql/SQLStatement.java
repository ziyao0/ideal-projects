package com.ziyao.ideal.usercenter.common.mysql;

/**
 * @author ziyao zhang
 */
public interface SQLStatement {


    void validate(Object o);

    <T> T execute();
}
