package com.ziyao.ideal.generator;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class CodeGenTests {
    public static void main(String[] args) {
        GenerationBootstrap generator = GenerationBootstrap.init("generator.properties");

        generator
                .strategyProperties(builder ->
                        builder.repositoryBuilder()
                                .superClass("tk.mybatis.mapper.common.Mapper")
                                .override()
                                .persistentBuilder()
                                .override()
                                .serviceBuilder()
                                .override()
                                .controllerBuilder()
                                .override())
                .bootstrap();
    }
}
