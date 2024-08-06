package com.ziyao.ideal.crypto.core;

import com.ziyao.ideal.crypto.Properties;
import com.ziyao.ideal.crypto.utils.ConstantPool;

/**
 * @author ziyao zhang
 */
public class DefaultProperties extends Properties<DefaultProperties> {
    @Override
    public String getPrefix() {
        return ConstantPool.default_prefix;
    }
}
