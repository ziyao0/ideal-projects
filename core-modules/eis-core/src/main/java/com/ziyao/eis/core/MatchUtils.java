package com.ziyao.eis.core;

import java.util.regex.Pattern;

/**
 * @author ziyao zhang
 */
public abstract class MatchUtils {
    private MatchUtils() {
    }

    /**
     * 正则匹配
     *
     * @param regex 正则表达式
     * @param input 匹配内容
     * @return 返回匹配结果 <code>true</code>匹配成功
     */
    public static boolean matches(String regex, CharSequence input) {
        return Pattern.matches(regex, input);
    }

    public static boolean matches(RegexPool regex, CharSequence input) {
        return Pattern.compile(regex.getRegex()).matcher(input).matches();
    }

}
