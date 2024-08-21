package com.ziyao.ideal.boot.autoconfigure.web.mvc;

import com.ziyao.ideal.web.ApplicationContextUtils;
import jakarta.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * 记录一个springboot3.x踩坑，他移除了原来的<code>spring.factories</code>方式加载外部bean，
 * 改用类似于<code>java spi</code>的形式
 * <p>
 * 现在文件的路径
 * META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * </p>
 *
 * @author ziyao zhang
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class WebMvcAutoConfiguration implements WebMvcConfigurer, ApplicationContextAware, InitializingBean {


    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }

    @Override
    public void afterPropertiesSet() {
        log.debug("AutoWebConfiguration initialization.");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 移除默认的 StringHttpMessageConverter
        converters.removeIf(StringHttpMessageConverter.class::isInstance);
    }
}
