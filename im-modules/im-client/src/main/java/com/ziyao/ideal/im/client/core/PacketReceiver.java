package com.ziyao.ideal.im.client.core;

import com.ziyao.ideal.im.api.Packet;
import io.netty.channel.Channel;

/**
 * @author ziyao zhang
 */
public interface PacketReceiver {
    void onReceive(Packet packet, Channel channel);
}
