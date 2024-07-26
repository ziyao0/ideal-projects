package com.ziyao.ideal.config.crypto.core;

import com.ziyao.ideal.config.core.OriginTrackedYamlProcessor;
import com.ziyao.ideal.core.Strings;
import com.ziyao.crypto.Property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ziyao
 */
public class YamlPropertySourceWriter implements PropertySourceWriter {


    @Override
    public void write(List<Property> properties, String location) throws IOException {

        String yamlStr = new OriginTrackedYamlProcessor(properties).resolve();
        Files.write(Paths.get(location), Strings.toBytesOrEmpty(yamlStr));
    }
}
