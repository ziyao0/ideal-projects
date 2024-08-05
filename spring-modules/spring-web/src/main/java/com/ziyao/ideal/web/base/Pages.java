package com.ziyao.ideal.web.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author ziyao zhang
 */
public abstract class Pages {


    public static <T> Page<T> initPage(PageParams<?> pageParams, Class<T> type) {

        return new Page<>(pageParams.getPage().getCurrent(), pageParams.getPage().getSize());
    }

    public static <T> Pageable initPage(PageParams<?> pageParams) {

        return PageRequest.of(pageParams.getPage().getCurrent(), pageParams.getPage().getSize());
    }
}
