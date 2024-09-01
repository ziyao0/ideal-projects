package com.ziyao.ideal.generator.engine;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.text.StrPool;
import com.ziyao.ideal.generator.ConfigSettings;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Freemarker 模板引擎实现文件输出
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine {

    private Configuration configuration;

    @Override
    public @NonNull FreemarkerTemplateEngine init(@NonNull ConfigSettings configSettings) {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StrPool.SLASH);
        return this;
    }


    @Override
    public void writer(@NonNull Map<String, Object> objectMap, @NonNull String templatePath, @NonNull File outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
        }
        LOGGER.debug("template:{};  file:{}", templatePath, outputFile);
    }


    @Override
    public @NonNull String templateFilePath(@NonNull String filePath) {
        return filePath + ".ftl";
    }
}
