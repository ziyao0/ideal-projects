package com.ziyao.ideal.web.proxy;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ziyao
 */
@Data
public class StopWatchConfig {

    /**
     * 总开关
     */
    private boolean enabled;
    /**
     * 任务列表
     */
    private Set<String> uniqCodes = new HashSet<>();
}
