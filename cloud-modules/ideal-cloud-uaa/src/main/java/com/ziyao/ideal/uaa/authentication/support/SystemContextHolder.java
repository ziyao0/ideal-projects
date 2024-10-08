package com.ziyao.ideal.uaa.authentication.support;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class SystemContextHolder {

    private static final String USER_AGENT = "User-Agent";

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static String getOS() {

        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        OSType osType = OSType.getByCode(request.getHeader(USER_AGENT));
        if (osType == null) {
            return null;
        }
        return osType.getOs();
    }

    public static String getBrowser() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        BrowserType browserType = BrowserType.getByCode(request.getHeader(USER_AGENT));
        if (browserType == null) {
            return null;
        }
        return browserType.getBrowser();
    }
}
