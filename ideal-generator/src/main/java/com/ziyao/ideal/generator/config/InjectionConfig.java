package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.builder.CustomFile;
import com.ziyao.ideal.generator.config.po.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 注入配置
 */
public class InjectionConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(InjectionConfig.class);

    /**
     * 输出文件之前消费者
     */
    private BiConsumer<TableInfo, Map<String, Object>> beforeOutputFileBiConsumer;

    /**
     * 自定义配置 Map 对象
     */
    private Map<String, Object> customMap = new HashMap<>();

    /**
     * 自定义模板文件列表
     */
    private final List<CustomFile> customFiles = new ArrayList<>();

    /**
     * 输出文件前
     */
    public void beforeOutputFile(TableInfo tableInfo, Map<String, Object> objectMap) {
        if (!customMap.isEmpty()) {
            objectMap.putAll(customMap);
            //增加一个兼容兼容取值,推荐还是直接取值外置key即可,例如abc取值${abc}而不需要${cfg.abc}
            objectMap.put("cfg", customMap);
        }
        if (null != beforeOutputFileBiConsumer) {
            beforeOutputFileBiConsumer.accept(tableInfo, objectMap);
        }
    }

    /**
     * 获取自定义配置 Map 对象
     */
    @NonNull
    public Map<String, Object> getCustomMap() {
        return customMap;
    }

    /**
     * 获取自定义模板文件列表
     */
    @NonNull
    public List<CustomFile> getCustomFiles() {
        return customFiles;
    }

    /**
     * 构建者
     */
    public static class Builder implements IConfigBuilder<InjectionConfig> {

        private final InjectionConfig injectionConfig;

        public Builder() {
            this.injectionConfig = new InjectionConfig();
        }

        /**
         * 输出文件之前消费者
         *
         * @param biConsumer 消费者
         * @return this
         */
        public Builder beforeOutputFile(@NonNull BiConsumer<TableInfo, Map<String, Object>> biConsumer) {
            this.injectionConfig.beforeOutputFileBiConsumer = biConsumer;
            return this;
        }

        /**
         * 自定义配置 Map 对象
         *
         * @param customMap Map 对象
         * @return this
         */
        public Builder customMap(@NonNull Map<String, Object> customMap) {
            this.injectionConfig.customMap = customMap;
            return this;
        }

        /**
         * 自定义配置模板文件
         *
         * @param customFile key为文件名称，value为文件路径
         * @return this
         */
        public Builder customFile(@NonNull Map<String, String> customFile) {
            return customFile(customFile.entrySet().stream()
                    .map(e -> new CustomFile.Builder().fileName(e.getKey()).templatePath(e.getValue()).build())
                    .collect(Collectors.toList()));
        }

        public Builder customFile(@NonNull CustomFile customFile) {
            this.injectionConfig.customFiles.add(customFile);
            return this;
        }

        public Builder customFile(@NonNull List<CustomFile> customFiles) {
            this.injectionConfig.customFiles.addAll(customFiles);
            return this;
        }

        public Builder customFile(Consumer<CustomFile.Builder> consumer) {
            CustomFile.Builder builder = new CustomFile.Builder();
            consumer.accept(builder);
            this.injectionConfig.customFiles.add(builder.build());
            return this;
        }

        @Override
        public InjectionConfig build() {
            return this.injectionConfig;
        }
    }

}
