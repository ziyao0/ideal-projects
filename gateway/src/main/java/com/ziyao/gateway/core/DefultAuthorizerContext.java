package com.ziyao.gateway.core;

import lombok.Data;

import java.util.Map;

/**
 * @author ziyao
 */
@Data
public class DefultAuthorizerContext implements AuthorizerContext {


    private Map<String, Object> attributes;
}
