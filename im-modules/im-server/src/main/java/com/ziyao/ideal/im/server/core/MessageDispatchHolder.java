package com.ziyao.ideal.im.server.core;

import com.ziyao.ideal.im.api.Event;
import com.ziyao.ideal.im.api.Packet;
import com.ziyao.ideal.im.core.DispatchHolder;
import io.netty.channel.group.ChannelGroup;

/**
 * @author ziyao zhang
 */
public class MessageDispatchHolder implements DispatchHolder {


    public void send(Packet packet) {
        switch (packet.getType()) {
            case BROADCAST: {
                NettySessionManager.getAll().writeAndFlush(packet);
            }
            case UNICAST: {
                ChannelGroup channels = NettySessionManager.get("");
                channels.writeAndFlush(packet);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + packet.getType());
        }
    }


    /**
     * User online resend but not executed{@link Event#OPEN}'S news
     *
     * @param userId userId
     */
    public void retry(String userId) {
    }

    /**
     * Message confirmation
     *
     * @param messageId message code
     * @see Event#ACK
     */
    public void ack(String messageId) {

    }


}
