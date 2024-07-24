package com.ziyao.ideal.mq.api;

/**
 * @author ziyao zhang
 */
public interface MQApi {

    void send(String topic, Message message);
}
