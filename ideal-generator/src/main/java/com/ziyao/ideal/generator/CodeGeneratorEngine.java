package com.ziyao.ideal.generator;

import com.ziyao.ideal.generator.engine.AbstractTemplateEngine;
import com.ziyao.ideal.generator.engine.FreemarkerTemplateEngine;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class CodeGeneratorEngine {
    private static final Logger log = LoggerFactory.getLogger(CodeGeneratorEngine.class);

    private GlobalSettings globalSettings;
    private DataSourceSettings dataSourceSettings;
    private StrategySettings strategySettings;
    private PackageSettings packageSettings;
    private ConfigSettings config;
    private final AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();


    private CodeGeneratorEngine() {
    }

    public CodeGeneratorEngine(DataSourceSettings dataSourceSettings) {
        this.dataSourceSettings = dataSourceSettings;
    }

    public CodeGeneratorEngine global(GlobalSettings globalSettings) {
        this.globalSettings = globalSettings;
        return this;
    }

    public CodeGeneratorEngine strategy(StrategySettings strategySettings) {
        this.strategySettings = strategySettings;
        return this;
    }

    public CodeGeneratorEngine packages(PackageSettings packageSettings) {
        this.packageSettings = packageSettings;
        return this;
    }

    public void generate() {
        log.info("准备生成文件");
        // 初始化配置
        this.config = new ConfigSettings(globalSettings, dataSourceSettings, strategySettings, packageSettings);

        templateEngine.setConfigSettings(config);
        // 模板引擎初始化执行文件输出
        templateEngine.init(config).batchOutput().open();
        log.debug("完成！");
    }
}
