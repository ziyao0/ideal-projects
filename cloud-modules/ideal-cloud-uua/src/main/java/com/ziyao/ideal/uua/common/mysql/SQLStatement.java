package com.ziyao.ideal.uua.common.mysql;

/**
 * @author ziyao zhang
 */
public interface SQLStatement {


    void validate(Object o);

    <T> T execute();
}
