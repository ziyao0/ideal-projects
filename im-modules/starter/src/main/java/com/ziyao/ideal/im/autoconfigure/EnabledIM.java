package com.ziyao.ideal.im.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ziyao zhang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AutoIMConfigurationImportSelector.class)
public @interface EnabledIM {
}
