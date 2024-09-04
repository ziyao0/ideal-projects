package com.ziyao.ideal.web.base;

import lombok.Data;

/**
 * @author ziyao zhang
 */
@Data
public class PageQuery<T> {

    private T data;

    private PageI page = new PageI();


    @Data
    public static class PageI {

        /**
         * 当前页条数
         */
        private int size = 10;

        /**
         * 当前页
         */
        private int current = 1;

    }
}
