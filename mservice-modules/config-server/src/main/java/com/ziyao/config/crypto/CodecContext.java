package com.ziyao.config.crypto;

/**
 * @author ziyao
 */
public interface CodecContext {

    EncryptCallback getEncryptCallback();

    DecryptCallback getDecryptCallback();
}
