package com.ziyao.ideal.config.crypto;

import com.ziyao.ideal.core.Assert;

import java.util.ServiceLoader;

/**
 * @author ziyao
 */
public abstract class CodecContextFactory {


    protected CodecContext createContext() {

        EncryptCallback encryptCallback = load(EncryptCallback.class);
        Assert.notNull(encryptCallback, "加密回调不能为空");
        DecryptCallback decryptCallback = load(DecryptCallback.class);
        Assert.notNull(decryptCallback, "解密回调不能为空");

        SpiCodecContext spiCodecContext = new SpiCodecContext();
        spiCodecContext.setEncryptCallback(encryptCallback);
        spiCodecContext.setDecryptCallback(decryptCallback);
        return spiCodecContext;
    }


    private <T> T load(Class<T> clazz) {
        ServiceLoader<T> services = ServiceLoader.load(clazz);
        return services.stream()
                .map(ServiceLoader.Provider::get)
                //按照排序取出最大优先级的
                .findFirst().orElse(null);
    }


    static CodecContextFactory getInstance() {
        return new CodecContextFactory() {
            @Override
            public CodecContext createContext() {
                return super.createContext();
            }
        };
    }
}
