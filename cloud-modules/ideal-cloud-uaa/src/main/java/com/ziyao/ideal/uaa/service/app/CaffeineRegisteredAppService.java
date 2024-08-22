package com.ziyao.ideal.uaa.service.app;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;

import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 */
public class CaffeineRegisteredAppService implements RegisteredAppService {

    private final Cache<Integer, RegisteredApp> registeredApps;

    public CaffeineRegisteredAppService() {
        registeredApps = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(7, TimeUnit.DAYS)
                .build();
    }


    @Override
    public void save(RegisteredApp registeredApp) {
        Assert.notNull(registeredApp, "registeredApp must not be null");
        registeredApps.put(registeredApp.getAppId(), registeredApp);
    }

    @Override
    public RegisteredApp findById(Integer appId) {
        return registeredApps.getIfPresent(appId);
    }

    @Override
    public Model model() {
        return Model.caffeine;
    }
}
