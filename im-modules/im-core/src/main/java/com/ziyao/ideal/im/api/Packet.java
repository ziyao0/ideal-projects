package com.ziyao.ideal.im.api;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;
import java.util.List;

/**
 * @author ziyao zhang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Packet implements Serializable {
    
    private static final long serialVersionUID = -130418831225718544L;

    private Event event;

    private Object data;

    private Type type;
    /**
     * 当接收人是多个的时候证明需要群发
     */
    private List<String> receivedBys;

    public Packet() {
    }

    public Packet(Event event, Object data) {
        this.event = event;
        this.data = data;
    }

    public Packet(Object data, String receivedBy) {
        this.data = data;
        this.type = Type.UNICAST;
        this.receivedBys = Lists.newArrayList(receivedBy);
    }

    public Packet(Object data) {
        this.data = data;
        this.event = Event.SEND;
        this.type = Type.BROADCAST;
    }

    public Packet(Object data, List<String> receivedBys) {
        this.data = data;
        this.type = Type.UNICAST;
        this.receivedBys = receivedBys;
    }

    public enum Type {
        BROADCAST,
        UNICAST,
        ;
    }
}
