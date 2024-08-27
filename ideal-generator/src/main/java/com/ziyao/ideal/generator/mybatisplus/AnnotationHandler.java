package com.ziyao.ideal.generator.mybatisplus;


import com.ziyao.ideal.generator.util.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 */
public interface AnnotationHandler {

    /**
     * 从类上获取注解
     *
     * @param beanClass       类的class
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 注解
     */
    default <T extends Annotation> T getAnnotation(Class<?> beanClass, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, beanClass);
    }

    /**
     * 判断类上是否存在注解
     *
     * @param beanClass       类的class
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 是否包含该注解
     */
    default <T extends Annotation> boolean isAnnotationPresent(Class<?> beanClass, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, beanClass) != null;
    }

    /**
     * 从字段上获取注解
     *
     * @param field           字段
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 注解
     */
    default <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, field);
    }

    /**
     * 判断字段上是否存在注解
     *
     * @param field           字段
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 是否包含该注解
     */
    default <T extends Annotation> boolean isAnnotationPresent(Field field, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, field) != null;
    }

    /**
     * 从方法上获取注解
     *
     * @param method          方法
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 注解
     */
    default <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, method);
    }

    /**
     * 判断方法上是否存在注解
     *
     * @param method          方法
     * @param annotationClass 要获取的注解class
     * @param <T>             具体注解
     * @return 是否包含该注解
     */
    default <T extends Annotation> boolean isAnnotationPresent(Method method, Class<T> annotationClass) {
        return AnnotationUtils.findFirstAnnotation(annotationClass, method) != null;
    }
}
