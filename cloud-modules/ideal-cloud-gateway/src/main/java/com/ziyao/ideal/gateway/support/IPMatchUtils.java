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

    private static final String separator = "\\.";

    public static boolean matchExactIp(Set<String> sources, String target) {
        if (isIPv4(target)) {
            if (Collections.isEmpty(sources)) {
                return true;
            }
            String[] ta = target.split(separator);
            for (String source : sources) {
                String[] sa = source.split(separator);
                if (sa.length != ta.length) {
                    continue;
                }
                return ta[0].equals(sa[0]) && sa[1].equals(ta[1]) && sa[2].equals(ta[2]) && sa[3].equals(ta[3]);
            }
        }
        return false;
    }

    public static boolean matchFuzzyIp(Set<String> sources, String target) {
        if (!isIPv4(target)) {
            return false;
        }
        return false;
    }

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
            logger.warn("IP address error {},sources:{},target:{}", e.getMessage(), sources.toArray(), target);
        }
        return false;
    }


    private static boolean isIPv4(String val) {
        if (Strings.hasText(val)) {
            return MatchUtils.matches(RegexPool.IP4, val);
        }
        return false;
    }

    public static boolean isIPv6(String val) {
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
