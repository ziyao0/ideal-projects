package com.ziyao.ideal.generator;

import com.ziyao.ideal.generator.settings.StrategySettings;

import java.util.function.Consumer;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class CodeGenTests {
    public static void main(String[] args) {
        GenerationBootstrap generator = GenerationBootstrap.init("generator.properties");

        generator
                .strategyProperties(new Consumer<StrategySettings.Builder>() {
                    @Override
                    public void accept(StrategySettings.Builder builder) {
                        builder.repositoryBuilder().superClass("tk.mybatis.mapper.common.Mapper")
                                .override()
                                .persistentBuilder()
                                .override()
                                .serviceBuilder()
                                .override()
                                .controllerBuilder()
                                .override();
                    }
                }).bootstrap();
    }
}
