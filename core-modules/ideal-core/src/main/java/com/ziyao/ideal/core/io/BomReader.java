package com.ziyao.ideal.core.io;

import com.ziyao.ideal.core.Assert;

import java.io.*;

/**
 * 读取带BOM头的流内容的Reader，如果非bom的流或无法识别的编码，则默认UTF-8<br>
 * BOM定义：<a href="http://www.unicode.org/unicode/faq/utf_bom.html"> BOM定义</a>
 *
 * <ul>
 * <li>00 00 FE FF = UTF-32, big-endian</li>
 * <li>FF FE 00 00 = UTF-32, little-endian</li>
 * <li>EF BB BF = UTF-8</li>
 * <li>FE FF = UTF-16, big-endian</li>
 * <li>FF FE = UTF-16, little-endian</li>
 * </ul>
 * 使用： <br>
 * <code>
 * FileInputStream fis = new FileInputStream(file); <br>
 * BomReader uin = new BomReader(fis); <br>
 * </code>
 *
 * @author ziyao zhang
 */
public class BomReader extends Reader {

    private InputStreamReader reader;

    /**
     * 构造
     *
     * @param in 流
     */
    public BomReader(InputStream in) {
        Assert.notNull(in, "InputStream must be not null!");
        final BOMInputStream bin = (in instanceof BOMInputStream) ? (BOMInputStream) in : new BOMInputStream(in);
        try {
            this.reader = new InputStreamReader(bin, bin.getCharset());
        } catch (UnsupportedEncodingException ignore) {
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
