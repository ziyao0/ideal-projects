package com.ziyao.ideal.im.client.core;

import com.ziyao.ideal.im.client.NettyClient;

import java.net.UnknownHostException;

/**
 * @author ziyao zhang
 */
public class ExceptionCaughtCallback {

    private final NettyClient nettyClient;

    public ExceptionCaughtCallback(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    private void reconnection() throws UnknownHostException {
        nettyClient.start();
    }


}
