package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum StrategyMode {

    MODE_THREAD_LOCAL("MODE_THREAD_LOCAL"),
    MODE_TTL("MODE_TTL"),
    MODE_DEBUG("MODE_DEBUG"),
    ;

    private final String strategyName;

    StrategyMode(String strategyName) {
        this.strategyName = strategyName;
    }

    private static final Map<String, StrategyMode> STRATEGY_MAP;

    static {
        STRATEGY_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(StrategyMode::getStrategyName, Function.identity()));
    }

    public static @NonNull StrategyMode getInstance(String strategyName) {
        if (Strings.hasText(strategyName)) {
            return STRATEGY_MAP.get(strategyName);
        }
        return MODE_THREAD_LOCAL;
    }
}
