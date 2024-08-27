package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.rules.DateType;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;


/**
 * 全局配置
 */
public class GlobalConfig {

    private GlobalConfig() {
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalConfig.class);

    /**
     * 生成文件的输出目录【 windows:D://  linux or mac:/tmp 】
     */
    @Getter
    private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";

    /**
     * 是否打开输出目录
     */
    @Getter
    private boolean open = true;

    /**
     * 作者
     */
    @Getter
    private String author = "ziyao";

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    private boolean swagger;
    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
    @Getter
    private boolean springdoc;

    /**
     * 时间类型对应策略
     */
    private DateType dateType = DateType.TIME_PACK;

    @Getter
    private boolean jpa = false;

    /**
     * 获取注释日期
     */
    private Supplier<String> commentDate = () -> new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public boolean isSwagger() {
        // springdoc 设置优先于 swagger
        return !springdoc && swagger;
    }

    @NonNull
    public DateType getDateType() {
        return dateType;
    }

    @NonNull
    public String getCommentDate() {
        return commentDate.get();
    }


    /**
     * 全局配置构建
     * <p>
     */
    public static class Builder implements IConfigBuilder<GlobalConfig> {

        private final GlobalConfig globalConfig;

        public Builder() {
            this.globalConfig = new GlobalConfig();
        }

        /**
         * 禁止打开输出目录
         */
        public Builder disableOpenDir() {
            this.globalConfig.open = false;
            return this;
        }

        public Builder enableJpa() {
            this.globalConfig.jpa = true;
            return this;
        }

        /**
         * 输出目录
         */
        public Builder outputDir(@NonNull String outputDir) {
            this.globalConfig.outputDir = outputDir;
            return this;
        }

        /**
         * 作者
         */
        public Builder author(@NonNull String author) {
            this.globalConfig.author = author;
            return this;
        }

        /**
         * 开启 swagger 模式
         */
        public Builder enableSwagger() {
            this.globalConfig.swagger = true;
            return this;
        }

        /**
         * 开启 springdoc 模式
         */
        public Builder enableSpringdoc() {
            this.globalConfig.springdoc = true;
            return this;
        }

        /**
         * 时间类型对应策略
         */
        public Builder dateType(@NonNull DateType dateType) {
            this.globalConfig.dateType = dateType;
            return this;
        }

        /**
         * 注释日期获取处理
         * example: () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
         *
         * @param commentDate 获取注释日期
         * @return this
         */
        public Builder commentDate(@NonNull Supplier<String> commentDate) {
            this.globalConfig.commentDate = commentDate;
            return this;
        }

        /**
         * 指定注释日期格式化
         *
         * @param pattern 格式
         * @return this
         */
        public Builder commentDate(@NonNull String pattern) {
            return commentDate(() -> new SimpleDateFormat(pattern).format(new Date()));
        }

        @Override
        public GlobalConfig build() {
            return this.globalConfig;
        }
    }
}
