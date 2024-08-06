package com.ziyao.ideal.crypto.core;

import com.ziyao.ideal.crypto.Codebook;
import com.ziyao.ideal.crypto.utils.ConstantPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ziyao zhang
 */
@Setter
@Getter
@ConfigurationProperties(ConstantPool.cipher_prefix)
public class CodebookProperties extends Codebook<CodebookProperties> {

    private String location;

    private boolean failOnError;

    public static CodebookProperties merge(
            CodebookProperties mainCodebook, CodebookProperties externalCodebook) {

        if (null == mainCodebook) {
            mainCodebook = new CodebookProperties();
        }
        if (null == externalCodebook) {
            externalCodebook = new CodebookProperties();
        }

        CodebookProperties properties = new CodebookProperties();

        properties.setSm4(KeyIv.merge(mainCodebook.getSm4(), externalCodebook.getSm4()));
        properties.setSm2(KeyPair.merge(mainCodebook.getSm2(), externalCodebook.getSm2()));
        properties.setTypes(mainCodebook.getTypes());
        properties.setLocation(mainCodebook.getLocation());
        properties.setFailOnError(mainCodebook.isFailOnError());
        return properties;
    }

    @Override
    public String getPrefix() {
        return ConstantPool.cipher_prefix;
    }

}
