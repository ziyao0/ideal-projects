package com.ziyao.ideal.im.server.adapter;

import com.ziyao.ideal.im.api.Agreement;
import com.ziyao.ideal.im.api.Packet;
import com.ziyao.ideal.im.server.core.HealthBeatProcessor;
import com.ziyao.ideal.im.server.core.MessageDispatchHolder;
import com.ziyao.ideal.im.server.core.NettySessionManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * @author ziyao zhang
 */
@ChannelHandler.Sharable
public class TCPHandlerAdapter extends SimpleChannelInboundHandler<Packet> {

    private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(TCPHandlerAdapter.class);

    private final MessageDispatchHolder messageDispatchHolder;

    public TCPHandlerAdapter(MessageDispatchHolder messageDispatchHolder) {
        this.messageDispatchHolder = messageDispatchHolder;
    }


    /**
     * 读取客户端发送的数据
     *
     * @param ctx    Context object, containing channels{@link io.netty.channel.Channel}
     *               channel{@link io.netty.channel.ChannelPipeline}
     * @param packet Data sent by the client
     * @see io.netty.channel.Channel
     * @see io.netty.channel.ChannelPipeline
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) {
        LOGGER.info("TCP Server send to message is {}", packet.getData());
        switch (packet.getEvent()) {
            case OPEN:
                // 请求
                processReadEvent(ctx, packet);
            case SEND: {
                // 发送消息
                LOGGER.debug("111{}", packet.getData());
            }
            case HEARTBEAT: {
                LOGGER.debug("Received heartbeat parameters:{}", packet);
                //健康检查pong
                new HealthBeatProcessor().process(ctx, Agreement.TCP);
            }
            case ACK:
                messageDispatchHolder.ack((String) packet.getData());
            default:
                LOGGER.error("Unknown processing status {}!", packet.getEvent());
        }
    }


    /**
     * Store the connection between the user and the pipeline
     *
     * @param ctx    Context object, containing channels{@link io.netty.channel.Channel}
     *               channel{@link io.netty.channel.ChannelPipeline}
     * @param packet 数据包
     */
    private void processReadEvent(ChannelHandlerContext ctx, Packet packet) {
        Channel channel = ctx.channel();
        String receivedBy = packet.getReceivedBys().get(0);
        // Asynchronously process user online retransmissions
        channel.eventLoop().submit(() -> {
            NettySessionManager.add(receivedBy, ctx.channel());
            //存储管道和用户id之间的关系
            //设置用户在线
            //User connection attempt message resend
            LOGGER.debug("[TCP]:{}", receivedBy);
            messageDispatchHolder.retry(receivedBy);
        });
    }
}
