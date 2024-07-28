package com.ziyao.ideal.web;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * spring application context utils
 *
 * @author zhangziyao
 */
public abstract class ApplicationContextUtils {

    /**
     * -- GETTER --
     * get spring application context
     */
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * Get bean of beanName
     *
     * @param beanName beanName
     * @param clazz    The corresponding class of the bean
     * @param <T>      Bean type obtained
     * @return Return bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * Obtain the corresponding bean through class
     *
     * @param clazz The corresponding class of the bean
     * @param <T>   Bean type obtained
     * @return Return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Obtain the corresponding bean list through class
     *
     * @param clazz The corresponding class of the bean
     * @param <T>   Bean type obtained
     * @return Return bean list
     */
    public static <T> List<T> getBeansOfType(Class<T> clazz) {
        Map<String, T> beansOfType = applicationContext.getBeansOfType(clazz);
        if (ObjectUtils.isEmpty(beansOfType)) {
            return Collections.emptyList();
        }
        Collection<T> collection = beansOfType.values();
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(collection);
    }

    /**
     * Obtain the corresponding bean map through class
     *
     * @param clazz The corresponding class of the bean
     * @param <T>   Bean type obtained
     * @return Return bean map
     */
    public static <T> Map<String, T> getBeansMapOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtils.applicationContext = applicationContext;
    }
}
