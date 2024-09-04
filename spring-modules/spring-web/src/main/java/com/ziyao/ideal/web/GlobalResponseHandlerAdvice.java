package com.ziyao.ideal.web;

import com.google.common.collect.Lists;
import com.ziyao.ideal.web.response.ResponseDetails;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Map;

/**
 * @author zhangziyao
 */
@RestControllerAdvice
public class GlobalResponseHandlerAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class converterType) {
        if (returnType != null) {
            Class<?> type = returnType.getParameterType();
            if (Exception.class.isAssignableFrom(type)) {
                return false;
            }
            return Object.class.isAssignableFrom(type);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object body, @Nullable MethodParameter parameter, @Nullable MediaType mediaType,
                                  @Nullable Class<? extends HttpMessageConverter<?>> converter, @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        if (body == null) {
            return ResponseBuilder.ok(null);
        }
        if (body instanceof ResponseDetails) {
            return body;
        }
        // 如果 body 是 Map 类型并且包含异常信息的字段
        if (body instanceof Map && ((Map) body).keySet().containsAll(paramNames)) {
            Map map = (Map) body;
            return ResponseBuilder.of(
                    Integer.parseInt(String.valueOf(map.get("status"))),
                    map.get("error").toString(),
                    map.get("path"));
        }
        // TODO 其他未知信息不做处理
        return body;

    }

    private static final List<String> paramNames = Lists.newArrayList("timestamp", "status", "error", "path");
}
