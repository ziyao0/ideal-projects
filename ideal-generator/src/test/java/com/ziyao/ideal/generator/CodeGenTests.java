package com.ziyao.ideal.generator;

import com.ziyao.ideal.generator.core.PersistType;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;

import java.util.function.Consumer;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class CodeGenTests {
    public static void main(String[] args) {
        CodeGenInitializer generator = CodeGenInitializer.init(
                "", "", "");

        generator.globalProperties(new Consumer<GlobalSettings.Builder>() {
                    @Override
                    public void accept(GlobalSettings.Builder builder) {
                        builder.subproject("code-repo")
                                .persistType(PersistType.TK_MYBATIS);
                    }
                })
                .packageProperties(new Consumer<PackageSettings.Builder>() {
                    @Override
                    public void accept(PackageSettings.Builder builder) {
                        builder.moduleName("repo");
                    }
                })
                .strategyProperties(new Consumer<StrategySettings.Builder>() {
                    @Override
                    public void accept(StrategySettings.Builder builder) {
                        builder.includes("application");
                        builder.repositoryBuilder().superClass("tk.mybatis.mapper.common.Mapper")
                                .override()
                                .persistentBuilder()
                                .override()
                                .serviceBuilder()
                                .override()
                                .controllerBuilder()
                                .override();
                    }
                }).generate();
    }
}
