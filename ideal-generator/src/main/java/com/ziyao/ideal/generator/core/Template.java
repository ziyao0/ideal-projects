package com.ziyao.ideal.generator.core;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface Template {
    /**
     * 是否生成文件
     *
     * @return <code>false</code> 不生成
     */
    boolean isGenerate();

    /**
     * 是否覆盖文件
     *
     * @return <code>true</code> 覆盖之前生成的文件
     */
    boolean isOverride();

    /**
     * 文件名称转换器
     *
     * @return convertor file name
     */
    OutputNameConvertor getConvertor();

    /**
     * 文件继承的父类
     * <p>
     * 如果返回null则表示没有继承信息
     *
     * @return 类路径名称
     */
    String getSuperClass();

    /**
     * 返回模板路径
     *
     * @return 模板信息
     */
    String getTemplate();

    /**
     * 加载渲染模板的元数据信息
     *
     * @param generatorContext 元数据信息
     * @return 返回渲染数据
     */
    Map<String, Object> load(GeneratorContext generatorContext);
}
