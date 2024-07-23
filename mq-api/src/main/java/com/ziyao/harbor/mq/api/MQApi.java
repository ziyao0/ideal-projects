package com.ziyao.harbor.mq.api;

/**
 * @author ziyao zhang
 */
public interface MQApi {

    void send(String topic, Message message);
}
