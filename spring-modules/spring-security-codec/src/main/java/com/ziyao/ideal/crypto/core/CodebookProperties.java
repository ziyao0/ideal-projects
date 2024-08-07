package com.ziyao.ideal.crypto.core;

import com.ziyao.ideal.core.Assert;
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

    private boolean failOnError = false;

    private boolean checkCipher = false;

    public static CodebookProperties merge(
            CodebookProperties localCodebook, CodebookProperties externalCodebook) {

        if (null == localCodebook) {
            localCodebook = new CodebookProperties();
        }
        if (null == externalCodebook) {
            externalCodebook = new CodebookProperties();
        }

        CodebookProperties properties = new CodebookProperties();

        properties.setSm4(KeyIv.merge(localCodebook.getSm4(), externalCodebook.getSm4()));
        properties.setSm2(KeyPair.merge(localCodebook.getSm2(), externalCodebook.getSm2()));
        properties.setTypes(localCodebook.getTypes());
        properties.setLocation(localCodebook.getLocation());
        properties.setFailOnError(localCodebook.isFailOnError());
        return properties;
    }

    @Override
    public String getPrefix() {
        return ConstantPool.cipher_prefix;
    }

}
