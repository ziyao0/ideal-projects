package com.ziyao.harbor.im.client.core;

import com.ziyao.harbor.im.api.Packet;
import io.netty.channel.Channel;

/**
 * @author ziyao zhang
 */
public interface PacketReceiver {
    void onReceive(Packet packet, Channel channel);
}
