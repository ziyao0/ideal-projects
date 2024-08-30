package com.ziyao.ideal.generator.properties;

import com.ziyao.ideal.generator.core.PersistType;
import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class GlobalProperties {

    private PersistType persistType = PersistType.JPA;

    private String outputDir = System.getProperty("user.dir") + "/src/main/java";

    private String author = "ziyao";

    private boolean swagger = false;

    private boolean springdoc = false;

    private String commentDate;

    private boolean open = false;

    public static class Builder {
        private final GlobalProperties globalProperties;

        public Builder() {
            this.globalProperties = new GlobalProperties();
        }

        public Builder persistType(PersistType persistType) {
            this.globalProperties.persistType = persistType;
            return this;
        }

        public Builder outputDir(String outputDir) {
            this.globalProperties.outputDir = outputDir;
            return this;
        }

        public Builder author(String author) {
            this.globalProperties.author = author;
            return this;
        }

        public Builder swagger(boolean swagger) {
            this.globalProperties.swagger = swagger;
            return this;
        }

        public Builder springdoc(boolean springdoc) {
            this.globalProperties.springdoc = springdoc;
            return this;
        }

        public Builder commentDate(String commentDate) {
            this.globalProperties.commentDate = commentDate;
            return this;
        }

        public Builder open(boolean open) {
            this.globalProperties.open = open;
            return this;
        }

        public GlobalProperties build() {
            return globalProperties;
        }
    }

    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        System.out.println(projectDir);
    }
}
