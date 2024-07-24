package com.ziyao.ideal.usercenter.common.mysql;

import java.sql.SQLException;

/**
 * @author ziyao zhang
 */
public interface QueryHandler {

    <T> T process(Class<T> clazz,
                  Object... args) throws SQLException;
}
