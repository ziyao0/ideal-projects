package com.ziyao.ideal.uaa.common.mysql;

/**
 * @author ziyao zhang
 */
public interface SQLStatement {


    void validate(Object o);

    <T> T execute();
}
