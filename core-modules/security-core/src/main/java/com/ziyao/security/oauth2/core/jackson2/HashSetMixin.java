package com.ziyao.security.oauth2.core.jackson2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Set;

/**
 * @author ziyao zhang
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
abstract class HashSetMixin {

    @JsonCreator
    HashSetMixin(Set<?> set) {
    }

}