package com.ziyao.ideal.gateway.authorization.factory;

import org.springframework.core.Ordered;

/**
 * @author ziyao zhang
 */
public abstract class AbstractHandler<T> implements Ordered {

    private AbstractHandler<T> nextAbstractHandler;

    /**
     * 关联链表信息
     *
     * @param nextAbstractHandler 节点数据
     */
    public void linkWith(AbstractHandler<T> nextAbstractHandler) {
        if (this.nextAbstractHandler == null) {
            this.nextAbstractHandler = nextAbstractHandler;
            return;
        }
        AbstractHandler<T> lastAbstractHandler = this.nextAbstractHandler;
        while (lastAbstractHandler.nextAbstractHandler != null) {
            lastAbstractHandler = lastAbstractHandler.nextAbstractHandler;
        }
        lastAbstractHandler.nextAbstractHandler = nextAbstractHandler;
    }

    /**
     * 检查链表是否存在下一个节点，如果存在则执行不存在链表执行完成
     *
     * @param t 处理对象
     */
    public void checkedNextHandler(T t) {
        if (null != nextAbstractHandler) {
            nextAbstractHandler.handle(t);
        }
    }

    /**
     * 继承处理器实现该方法
     * 该方法抛出异常，执行链路中断
     *
     * @param t 入参对象
     */
    public abstract void handle(T t);


}
