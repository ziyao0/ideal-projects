package com.ziyao.harbor.crypto.core;

import com.ziyao.crypto.Property;

import java.io.IOException;
import java.util.List;

/**
 * @author ziyao
 */
public interface PropertySourceWriter {


    void write(List<Property> properties, String location) throws IOException;
}
