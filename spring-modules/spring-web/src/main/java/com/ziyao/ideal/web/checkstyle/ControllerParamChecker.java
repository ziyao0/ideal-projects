package com.ziyao.ideal.web.checkstyle;

import com.ziyao.ideal.web.ApplicationContextUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.ziyao.ideal.core.lang.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 对控制层方法参数进行校验
 * <p>
 * 严谨控制层使用实体类来做方法参数
 * <p>
 * 判断标准：
 * 检查参数中的实体类是否包含一下注解
 *
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class ControllerParamChecker implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {


    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        ApplicationContext context = ApplicationContextUtils.getApplicationContext();

        Map<String, Object> controllerBeans = context.getBeansWithAnnotation(Controller.class);

        controllerBeans.putAll(context.getBeansWithAnnotation(RestController.class));

        for (Object bean : controllerBeans.values()) {

            for (Method method : bean.getClass().getDeclaredMethods()) {

                for (Parameter parameter : method.getParameters()) {

                    Class<?> parameterType = parameter.getType();

                    if (parameterType.isAnnotationPresent(Entity.class)
                            || parameterType.isAnnotationPresent(Table.class)) {
                        throw new RuntimeException("控制层不允许使用实体类作为方法参数，请使用DTO替换。" +
                                "\n Controller bean: " + bean.getClass().getName() +
                                "\n Method Name: " + method.getName() +
                                "\n Parameter: " + parameterType.getName());
                    }
                }
            }
        }
    }


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }
}
