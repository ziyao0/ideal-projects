package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.core.metrics.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao zhang
 */
public abstract class GatewayStopWatches {

    private static final Logger log = LoggerFactory.getLogger(GatewayStopWatches.class);
    private static final String STOP_WATCH_ID = "gateway_stopwatch";
    private static final String STOP_WATCH_ENABLED = "gateway_stopwatch_enabled";

    /**
     * 开始记录任务执行信息
     *
     * @param taskId   任务id
     * @param exchange 用于 HTTP 请求-响应交互
     */
    public static void start(final String taskId, ServerWebExchange exchange) {
        if (isEnabled(exchange)) {
            StopWatch stopWatch = exchange.getAttribute(STOP_WATCH_ID);
            if (stopWatch == null) {
                stopWatch = new StopWatch(STOP_WATCH_ID);
                exchange.getAttributes().put(STOP_WATCH_ID, stopWatch);
            }
            stopWatch.start(taskId);
        }
    }

    /**
     * 停止当前记录的任务
     *
     * @param taskId   任务id
     * @param exchange 用于 HTTP 请求-响应交互
     */
    public static void stop(final String taskId, ServerWebExchange exchange) {
        if (isEnabled(exchange)) {
            StopWatch stopWatch = getStopWatch(exchange);
            if (stopWatch == null) {
                throw new IllegalArgumentException("当前没有任务正在执行！");
            }
            stopWatch.stop(taskId);
        }
    }

    /**
     * 停止当前记录的任务
     *
     * @param taskId   任务id
     * @param exchange 用于 HTTP 请求-响应交互
     */
    public static void stopIfRunning(final String taskId, ServerWebExchange exchange) {
        if (isEnabled(exchange)) {
            StopWatch stopWatch = getStopWatch(exchange);
            if (stopWatch == null) {
                throw new IllegalArgumentException("当前没有任务正在执行！");
            }
            if (stopWatch.isRunning()) {
                stopWatch.stop(taskId);
            }
        }
    }

    /**
     * 获取记录任务执行时间的秒表
     *
     * @param exchange 用于 HTTP 请求-响应交互
     * @return {@link StopWatch}
     */
    public static StopWatch getStopWatch(ServerWebExchange exchange) {
        if (isEnabled(exchange))
            return exchange.getAttribute(STOP_WATCH_ID);
        else
            return null;
    }

    /**
     * 打印秒表监控信息
     *
     * @param exchange 用于 HTTP 请求-响应交互
     */
    public static void prettyPrint(ServerWebExchange exchange) {
        StopWatch stopWatch = getStopWatch(exchange);
        if (stopWatch != null) {
            log.info(stopWatch.prettyPrint());
        }
    }

    /**
     * 返回总耗时
     *
     * @param exchange 用于 HTTP 请求-响应交互
     * @return 请求执行总时长
     */
    public static long elapsedTime(ServerWebExchange exchange) {
        StopWatch stopWatch = getStopWatch(exchange);
        if (stopWatch != null) {
            return stopWatch.getTotalTimeMillis();
        }
        return 0;
    }

    public static boolean isEnabled(ServerWebExchange exchange) {
        return Boolean.TRUE.equals(exchange.getAttribute(STOP_WATCH_ENABLED));

    }

    public static void enable(ServerWebExchange exchange) {
        exchange.getAttributes().put(STOP_WATCH_ENABLED, true);
    }

    public static void disable(ServerWebExchange exchange) {
        exchange.getAttributes().put(STOP_WATCH_ENABLED, false);
    }

    private GatewayStopWatches() {
    }
}
