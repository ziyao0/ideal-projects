package com.ziyao.ideal.generator.settings;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.ConfigurationProperties;
import com.ziyao.ideal.generator.core.PersistType;
import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
@ConfigurationProperties(prefix = "global")
public class GlobalSettings {

    private static final String JAVA_DIR = "/src/main/java";
    private static final String XML_DIR = "/src/main/resources";

    private PersistType persistType = PersistType.jpa;

    private String persist;

    private String outputDir = System.getProperty("user.dir");

    private String subproject;

    private String author = "ziyao";

    private boolean swagger = false;

    private boolean springdoc = false;

    private String commentDate;

    private boolean open = false;

    public PersistType getPersistType() {
        if (Strings.hasText(this.persist)) {
            return PersistType.valueOf(this.persist);
        }
        return persistType;
    }

    public String getOutputDir() {
        if (Strings.hasText(subproject)) {
            return outputDir + "/" + subproject + JAVA_DIR;
        }
        return outputDir + JAVA_DIR;
    }

    public String getXmlOutputDir() {
        if (Strings.hasText(subproject)) {
            return outputDir + "/" + subproject + XML_DIR;
        }
        return outputDir + XML_DIR;
    }

    public static class Builder {
        private final GlobalSettings globalSettings;

        public Builder() {
            this.globalSettings = new GlobalSettings();
        }

        public Builder(GlobalSettings globalSettings) {
            this.globalSettings = globalSettings;
        }

        public Builder persistType(PersistType persistType) {
            this.globalSettings.persistType = persistType;
            return this;
        }

        public Builder outputDir(String outputDir) {
            this.globalSettings.outputDir = outputDir;
            return this;
        }

        public Builder subproject(String subproject) {
            this.globalSettings.subproject = subproject;
            return this;
        }

        public Builder author(String author) {
            this.globalSettings.author = author;
            return this;
        }

        public Builder swagger(boolean swagger) {
            this.globalSettings.swagger = swagger;
            return this;
        }

        public Builder springdoc(boolean springdoc) {
            this.globalSettings.springdoc = springdoc;
            return this;
        }

        public Builder commentDate(String commentDate) {
            this.globalSettings.commentDate = commentDate;
            return this;
        }

        public Builder open(boolean open) {
            this.globalSettings.open = open;
            return this;
        }

        public GlobalSettings build() {
            return globalSettings;
        }
    }
}
