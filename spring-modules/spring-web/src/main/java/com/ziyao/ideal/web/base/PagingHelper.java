package com.ziyao.ideal.web.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author ziyao zhang
 */
public abstract class PagingHelper {


    public static <T> Pageable initPage(PageQuery<?> pageQuery) {
        return PageRequest.of(pageQuery.getPage().getCurrent() - 1, pageQuery.getPage().getSize());
    }
}
