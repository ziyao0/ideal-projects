package com.ziyao.harbor.crypto;

/**
 * @author ziyao
 */
public interface CodecContext {

    EncryptCallback getEncryptCallback();

    DecryptCallback getDecryptCallback();
}
