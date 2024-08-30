package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.engine.AbstractTemplateEngine;
import com.ziyao.ideal.generator.properties.DataSourceProperties;
import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.PackageProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class AutoGenerator {

    /**
     * 数据源配置 Builder
     */
    private final DataSourceProperties.Builder dataSourcePropertiesBuilder;

    /**
     * 全局配置 Builder
     */
    private final GlobalProperties.Builder globalPropertiesBuilder;

    /**
     * 包配置 Builder
     */
    private final PackageProperties.Builder packagePropertiesBuilder;

    /**
     * 策略配置 Builder
     */
    private final StrategyProperties.Builder strategyPropertiesBuilder;


    /**
     * 模板引擎
     */
    private AbstractTemplateEngine templateEngine;

    private AutoGenerator(DataSourceProperties.Builder dataSourcePropertiesBuilder) {
        this.dataSourcePropertiesBuilder = dataSourcePropertiesBuilder;
        this.globalPropertiesBuilder = new GlobalProperties.Builder();
        this.packagePropertiesBuilder = new PackageProperties.Builder();
        this.strategyPropertiesBuilder = new StrategyProperties.Builder();
    }

    public static AutoGenerator create(@NonNull String url, String username, String password) {
        return new AutoGenerator(new DataSourceProperties.Builder(url, username, password));
    }

    public static AutoGenerator create(@NonNull DataSourceProperties.Builder dataSourcePropertiesBuilder) {
        return new AutoGenerator(dataSourcePropertiesBuilder);
    }

    /**
     * 读取控制台输入内容
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * 控制台输入内容读取并打印提示信息
     *
     * @param message 提示信息
     * @return String
     */
    public String scannerNext(String message) {
        System.out.println(message);
        String nextLine = scanner.nextLine();
        if (Strings.isEmpty(nextLine)) {
            // 如果输入空行继续等待
            return scanner.next();
        }
        return nextLine;
    }

    /**
     * 全局配置
     *
     * @param consumer 自定义全局配置
     * @return AutoGenerator
     */
    public AutoGenerator dataSourceProperties(Consumer<DataSourceProperties.Builder> consumer) {
        consumer.accept(this.dataSourcePropertiesBuilder);
        return this;
    }

    public AutoGenerator dataSourceProperties(BiConsumer<Function<String, String>, DataSourceProperties.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.dataSourcePropertiesBuilder);
        return this;
    }

    /**
     * 全局配置
     *
     * @param consumer 自定义全局配置
     * @return AutoGenerator
     */
    public AutoGenerator globalProperties(Consumer<GlobalProperties.Builder> consumer) {
        consumer.accept(this.globalPropertiesBuilder);
        return this;
    }

    public AutoGenerator globalProperties(BiConsumer<Function<String, String>, GlobalProperties.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.globalPropertiesBuilder);
        return this;
    }

    /**
     * 包配置
     *
     * @param consumer 自定义包配置
     * @return AutoGenerator
     */
    public AutoGenerator packageProperties(Consumer<PackageProperties.Builder> consumer) {
        consumer.accept(this.packagePropertiesBuilder);
        return this;
    }

    public AutoGenerator packageProperties(BiConsumer<Function<String, String>, PackageProperties.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.packagePropertiesBuilder);
        return this;
    }

    /**
     * 策略配置
     *
     * @param consumer 自定义策略配置
     * @return AutoGenerator
     */
    public AutoGenerator strategyProperties(Consumer<StrategyProperties.Builder> consumer) {
        consumer.accept(this.strategyPropertiesBuilder);
        return this;
    }

    public AutoGenerator strategyProperties(BiConsumer<Function<String, String>, StrategyProperties.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.strategyPropertiesBuilder);
        return this;
    }


    /**
     * 模板引擎配置
     *
     * @param templateEngine 模板引擎
     * @return AutoGenerator
     */
    public AutoGenerator templateEngine(AbstractTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }

    public void execute() {
        new GenerationEngine(this.dataSourcePropertiesBuilder.build())
                // 全局配置
                .global(this.globalPropertiesBuilder.build())
                // 包配置
                .packages(this.packagePropertiesBuilder.build())
                // 策略配置
                .strategy(this.strategyPropertiesBuilder.build())
                // 执行
                .execute();
    }
}
