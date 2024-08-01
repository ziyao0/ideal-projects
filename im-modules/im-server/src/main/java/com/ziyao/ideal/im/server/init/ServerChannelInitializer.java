package com.ziyao.ideal.im.server.init;

import com.ziyao.ideal.im.codec.PacketDecoder;
import com.ziyao.ideal.im.codec.PacketEncoder;
import com.ziyao.ideal.im.server.adapter.ShareHandlerAdapter;
import com.ziyao.ideal.im.server.adapter.TCPHandlerAdapter;
import com.ziyao.ideal.im.server.core.MessageDispatchHolder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.NonNull;

/**
 * Netty Server Channel initializer {@link ChannelInitializer<SocketChannel>}
 *
 * @author ziyao zhang
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(@NonNull SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
//        PipelineHolder.createPipeline(channel);
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new PacketEncoder());
        pipeline.addLast(new ShareHandlerAdapter());
        pipeline.addLast(new TCPHandlerAdapter(new MessageDispatchHolder()));
    }


}
