package com.ziyao.ideal.usercenter.authentication.processors;

import com.ziyao.ideal.data.redis.core.convert.ConversionProvider;
import com.ziyao.ideal.usercenter.common.utils.RedisUtils;
import com.ziyao.ideal.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 监控指标
 *
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class MonitoringMetricsPostProcessor implements AuthenticationPostProcessor {


    private final ConversionProvider provider = ConversionProvider.getInstance();

    private final StringRedisTemplate redisOps;

    @Override
    public Authentication process(Authentication authentication) {

        // 记录指标，比如总登录人数，当月登录人数，当天登录人数，在线人数等


        RedisCallback<Void> command = connection -> {

            connection.incr(provider.convert(RedisUtils.Metrics.getTotalKey(), byte[].class));
            connection.incr(provider.convert(RedisUtils.Metrics.getYearKey(), byte[].class));
            connection.incr(provider.convert(RedisUtils.Metrics.getMonthKey(), byte[].class));
            connection.incr(provider.convert(RedisUtils.Metrics.getDayKey(), byte[].class));
            connection.incr(provider.convert(RedisUtils.Metrics.getOnlineKey(), byte[].class));

            return null;
        };
        redisOps.execute(command);
        //
        return authentication;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
