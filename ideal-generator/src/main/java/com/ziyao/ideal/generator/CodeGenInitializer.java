package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.PersistType;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class CodeGenInitializer {

    /**
     * 数据源配置 Builder
     */
    private final DataSourceSettings.Builder dataSourcePropertiesBuilder;

    /**
     * 全局配置 Builder
     */
    private final GlobalSettings.Builder globalPropertiesBuilder;

    /**
     * 包配置 Builder
     */
    private final PackageSettings.Builder packagePropertiesBuilder;

    /**
     * 策略配置 Builder
     */
    private final StrategySettings.Builder strategyPropertiesBuilder;

    private CodeGenInitializer(DataSourceSettings.Builder dataSourcePropertiesBuilder) {
        this.dataSourcePropertiesBuilder = dataSourcePropertiesBuilder;
        this.globalPropertiesBuilder = new GlobalSettings.Builder();
        this.packagePropertiesBuilder = new PackageSettings.Builder();
        this.strategyPropertiesBuilder = new StrategySettings.Builder();
    }

    public static CodeGenInitializer init(@NonNull String url, String username, String password) {
        return new CodeGenInitializer(new DataSourceSettings.Builder(url, username, password));
    }

    public static CodeGenInitializer init(@NonNull DataSourceSettings.Builder dataSourcePropertiesBuilder) {
        return new CodeGenInitializer(dataSourcePropertiesBuilder);
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
    public CodeGenInitializer dataSourceProperties(Consumer<DataSourceSettings.Builder> consumer) {
        consumer.accept(this.dataSourcePropertiesBuilder);
        return this;
    }

    public CodeGenInitializer dataSourceProperties(BiConsumer<Function<String, String>, DataSourceSettings.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.dataSourcePropertiesBuilder);
        return this;
    }

    /**
     * 全局配置
     *
     * @param consumer 自定义全局配置
     * @return AutoGenerator
     */
    public CodeGenInitializer globalProperties(Consumer<GlobalSettings.Builder> consumer) {
        consumer.accept(this.globalPropertiesBuilder);
        return this;
    }

    public CodeGenInitializer globalProperties(BiConsumer<Function<String, String>, GlobalSettings.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.globalPropertiesBuilder);
        return this;
    }

    /**
     * 包配置
     *
     * @param consumer 自定义包配置
     * @return AutoGenerator
     */
    public CodeGenInitializer packageProperties(Consumer<PackageSettings.Builder> consumer) {
        consumer.accept(this.packagePropertiesBuilder);
        return this;
    }

    public CodeGenInitializer packageProperties(BiConsumer<Function<String, String>, PackageSettings.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.packagePropertiesBuilder);
        return this;
    }

    /**
     * 策略配置
     *
     * @param consumer 自定义策略配置
     * @return AutoGenerator
     */
    public CodeGenInitializer strategyProperties(Consumer<StrategySettings.Builder> consumer) {
        consumer.accept(this.strategyPropertiesBuilder);
        return this;
    }

    public CodeGenInitializer strategyProperties(BiConsumer<Function<String, String>, StrategySettings.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.strategyPropertiesBuilder);
        return this;
    }

    public void generate() {
        new CodeGeneratorEngine(this.dataSourcePropertiesBuilder.build())
                // 全局配置
                .global(this.globalPropertiesBuilder.build())
                // 包配置
                .packages(this.packagePropertiesBuilder.build())
                // 策略配置
                .strategy(this.strategyPropertiesBuilder.build())
                // 执行
                .generate();
    }

    public static void main(String[] args) {
        CodeGenInitializer generator = CodeGenInitializer.init("", "", "");

        generator.globalProperties(new Consumer<GlobalSettings.Builder>() {
                    @Override
                    public void accept(GlobalSettings.Builder builder) {
                        builder.outputDir(System.getProperty("user.dir") + "/ideal-generator-pro" + "/src/main/java");
                        builder.persistType(PersistType.MYBATIS_PLUS);
                    }
                })
                .packageProperties(new Consumer<PackageSettings.Builder>() {
                    @Override
                    public void accept(PackageSettings.Builder builder) {
                        builder.moduleName("code");
                    }
                })
                .strategyProperties(new Consumer<StrategySettings.Builder>() {
                    @Override
                    public void accept(StrategySettings.Builder builder) {
                        builder.includes("application");
                        builder.repositoryBuilder().superClass("org.springframework.data.jpa.repository.JpaRepository");
                    }
                }).generate();
    }
}
