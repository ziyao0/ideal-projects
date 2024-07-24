package com.ziyao.ideal.core.codec;

import com.ziyao.ideal.core.HexUtils;

import java.nio.charset.Charset;

/**
 * @author ziyao zhang
 */
public class HexCodec implements StringCodec {


    public static final HexCodec CODEC = new HexCodec();


    @Override
    public String encode(byte[] data) {
        return HexUtils.encodeHexStr(data);
    }

    @Override
    public String encode(byte[] data, Charset charset) {
        return HexUtils.encodeHexStr(data);
    }

    @Override
    public byte[] decode(CharSequence encoded) {
        return HexUtils.decodeHex(encoded);
    }

    @Override
    public byte[] decode(CharSequence encoded, Charset charset) {
        return HexUtils.decodeHex(encoded);
    }
}
