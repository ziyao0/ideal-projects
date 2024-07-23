package com.ziyao.harbor.im.core;

/**
 * @author ziyao zhang
 */
public interface Listener {
    /**
     * Listener The monitoring service started successfully
     *
     * @param args args
     */
    void success(Object... args);

    /**
     * Listener service startup exception
     *
     * @param cause Throwable
     */
    void failure(Throwable cause);
}
