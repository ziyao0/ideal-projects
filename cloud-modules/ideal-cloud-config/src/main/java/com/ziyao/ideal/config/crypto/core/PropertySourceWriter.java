package com.ziyao.ideal.config.crypto.core;

import com.ziyao.ideal.crypto.Property;

import java.io.IOException;
import java.util.List;

/**
 * @author ziyao
 */
public interface PropertySourceWriter {


    void write(List<Property> properties, String location) throws IOException;
}
