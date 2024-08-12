package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.MatchUtils;
import com.ziyao.ideal.core.RegexPool;
import com.ziyao.ideal.core.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * @author ziyao zhang
 */
public abstract class IPMatchUtils {

    private static final Logger logger = LoggerFactory.getLogger(IPMatchUtils.class);

    /**
     * 精确匹配
     *
     * @return <code>true</code> 匹配成功
     */
    public static boolean matchExactIp(Set<String> sources, String target) {
        try {
            if (isIPv4(target)) {
                if (Collections.isEmpty(sources)) {
                    return false;
                }
                InetAddress addr1 = InetAddress.getByName(target);
                for (String source : sources) {
                    InetAddress addr2 = InetAddress.getByName(source);
                    if (addr1.equals(addr2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("matchExactIp error {},sources:{},target:{}", e.getMessage(), sources.toArray(), target);
        }
        return false;
    }

    /**
     * 模糊匹配
     *
     * @return <code>true</code> 匹配成功
     */
    public static boolean matchFuzzyIp(Set<String> sources, String target) {
        try {
            if (!isIPv4(target)) {
                return false;
            }
            if (Collections.isEmpty(sources)) {
                return false;
            }
            byte[] ipBytes = InetAddress.getByName(target).getAddress();
            for (String source : sources) {
                // Convert IP and pattern to byte arrays
                byte[] patternBytes = InetAddress.getByName(source.replace("*", "0")).getAddress();

                // Check each byte of IP address against the pattern
                for (int i = 0; i < ipBytes.length; i++) {
                    if (patternBytes[i] != 0 && ipBytes[i] != patternBytes[i]) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            logger.warn("matchFuzzyIp error {},sources:{},target:{}", e.getMessage(), sources.toArray(), target);
        }
        return false;
    }

    /**
     * 范围匹配
     *
     * @return <code>true</code> 匹配成功
     */
    public static boolean matchIpRange(Set<String> sources, String target) {
        try {
            if (!isIPv4(target)) {
                return false;
            }
            if (Collections.isEmpty(sources)) {
                return false;
            }
            String breakLine = "~";
            for (String source : sources) {
                String[] sArr = source.split(breakLine);
                if (sArr.length != 2) {
                    return false;
                }
                long ipNumeric = ipToLong(InetAddress.getByName(target).getAddress());
                long startNumeric = ipToLong(InetAddress.getByName(sArr[0]).getAddress());
                long endNumeric = ipToLong(InetAddress.getByName(sArr[1]).getAddress());
                return ipNumeric >= startNumeric && ipNumeric <= endNumeric;
            }
        } catch (UnknownHostException e) {
            logger.warn("matchIpRange error {},sources:{},target:{}", e.getMessage(), sources.toArray(), target);
        }
        return false;
    }


    private static boolean isIPv4(String val) {
        if (Strings.hasText(val)) {
            return MatchUtils.matches(RegexPool.IP4, val);
        }
        return false;
    }

    private static boolean isIPv6(String val) {
        if (Strings.hasText(val)) {
            return MatchUtils.matches(RegexPool.IPV6, val);
        }
        return false;
    }

    private static long ipToLong(byte[] ipAddress) {
        long ip = 0;
        for (byte b : ipAddress) {
            ip = (ip << 8) + (b & 0xFF);
        }
        return ip;
    }

    private IPMatchUtils() {
    }
}
