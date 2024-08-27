package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.generator.config.IConfigBuilder;
import com.ziyao.ideal.generator.config.po.TableInfo;
import lombok.Getter;

import java.util.function.Function;

/**
 * 自定义模板文件配置
 */
@Getter
public class CustomFile {

    /**
     * 文件名称格式化函数
     */
    private Function<TableInfo, String> formatNameFunction;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 模板路径
     */
    private String templatePath;

    /**
     * 自定义文件包名
     */
    private String packageName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 是否覆盖已有文件（默认 false）
     */
    private boolean fileOverride;

    /**
     * 构建者
     */
    public static class Builder implements IConfigBuilder<CustomFile> {

        private final CustomFile customFile;

        public Builder() {
            this.customFile = new CustomFile();
        }

        /**
         * 文件名称格式化函数
         */
        public Builder formatNameFunction(Function<TableInfo, String> formatNameFunction) {
            this.customFile.formatNameFunction = formatNameFunction;
            return this;
        }

        /**
         * 文件名称
         */
        public Builder fileName(String fileName) {
            this.customFile.fileName = fileName;
            return this;
        }

        /**
         * 模板路径
         */
        public Builder templatePath(String templatePath) {
            this.customFile.templatePath = templatePath;
            return this;
        }

        /**
         * 包路径
         */
        public Builder packageName(String packageName) {
            this.customFile.packageName = packageName;
            return this;
        }

        /**
         * 文件路径，默认为 PackageConfig.parent 路径
         */
        public Builder filePath(String filePath) {
            this.customFile.filePath = filePath;
            return this;
        }

        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.customFile.fileOverride = true;
            return this;
        }

        @Override
        public CustomFile build() {
            return this.customFile;
        }
    }
}
