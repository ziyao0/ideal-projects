package com.ziyao.ideal.uaa.authentication.support;

import com.ziyao.ideal.core.Strings;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum BrowserType {

    IE("MSIE", "IE"),
    FIREFOX("Firefox", "Firefox"),
    CHROME("Chrome", "Chrome"),
    SAFARI("Safari", "Safari"),
    CAMINO("Camino", "Camino"),
    KONQUEROR("Konqueror", "Konqueror"),
    GECKO("Gecko", "360浏览器"),
    ;

    private final String code;

    private final String browser;

    BrowserType(String code, String browser) {
        this.code = code;
        this.browser = browser;
    }

    private static final Map<String, BrowserType> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(BrowserType::getCode, Function.identity()));
    }

    public static BrowserType getByCode(String code) {
        if (Strings.isEmpty(code)) {
            return null;
        }
        for (Map.Entry<String, BrowserType> entry : map.entrySet()) {
            if (code.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
