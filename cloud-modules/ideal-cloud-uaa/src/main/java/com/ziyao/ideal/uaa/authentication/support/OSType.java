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
public enum OSType {

    WIN2018("NT 6.0", "Windows Vista/Server 2008"),
    WIN2013("NT 5.2", "Windows Server 2003"),
    WIN_XP("NT 5.1", "Windows XP"),
    WIN7("NT 6.1", "Windows 7"),
    WIN_SLATE("NT 6.2", "Windows Slate"),
    WIN9("NT 6.3", "Windows 9"),
    WIN10("NT 10.0", "Windows 10"),
    WIN2000("NT 5", "Windows 2000"),
    WINNT4("NT 4", "Windows NT4"),
    WIN_ME("Me", "Windows Me"),
    WIN98("98", "Windows 98"),
    WIN95("95", "Windows 95"),
    MAC("Mac", "Mac"),
    UNIX("Unix", "UNIX"),
    LINUX("Linux", "Linux"),
    SUNOS("SunOS", "SunOS"),
    FREEBSD("FreeBSD", "FreeBSD"),
    ;

    private final String code;

    private final String os;

    OSType(String code, String os) {
        this.code = code;
        this.os = os;
    }


    private static final Map<String, OSType> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(OSType::getCode, Function.identity()));
    }

    public static OSType getByCode(String code) {
        if (Strings.isEmpty(code)) {
            return null;
        }
        for (Map.Entry<String, OSType> entry : map.entrySet()) {
            if (code.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
