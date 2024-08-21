package com.ziyao.ideal.web.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author ziyao zhang
 */
public abstract class Pages {


    public static <T> Pageable initPage(PageParams<?> pageParams) {

        return PageRequest.of(pageParams.getPage().getCurrent(), pageParams.getPage().getSize());
    }
}
