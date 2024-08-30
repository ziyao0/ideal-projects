package com.ziyao.ideal.generator;

import com.ziyao.ideal.generator.engine.AbstractTemplateEngine;
import com.ziyao.ideal.generator.engine.FreemarkerTemplateEngine;
import com.ziyao.ideal.generator.properties.DataSourceProperties;
import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.PackageProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class GenerationEngine {
    private static final Logger log = LoggerFactory.getLogger(GenerationEngine.class);

    private GlobalProperties globalProperties;
    private DataSourceProperties dataSourceProperties;
    private StrategyProperties strategyProperties;
    private PackageProperties packageProperties;
    private ConfigManager config;
    private final AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();


    private GenerationEngine() {
    }

    public GenerationEngine(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public GenerationEngine global(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
        return this;
    }

    public GenerationEngine strategy(StrategyProperties strategyProperties) {
        this.strategyProperties = strategyProperties;
        return this;
    }

    public GenerationEngine packages(PackageProperties packageProperties) {
        this.packageProperties = packageProperties;
        return this;
    }

    public void execute() {
        log.info("准备生成文件");
        // 初始化配置
        this.config = new ConfigManager(globalProperties, dataSourceProperties, strategyProperties, packageProperties);

        templateEngine.setConfigManager(config);
        // 模板引擎初始化执行文件输出
        templateEngine.init(config).batchOutput().open();
        log.debug("完成！");
    }
}
