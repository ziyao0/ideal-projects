package com.ziyao.ideal.config.crypto;

/**
 * @author ziyao
 */
public interface CodecContext {

    EncryptCallback getEncryptCallback();

    DecryptCallback getDecryptCallback();
}
