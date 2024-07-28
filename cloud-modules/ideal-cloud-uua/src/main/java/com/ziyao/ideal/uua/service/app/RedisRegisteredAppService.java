package com.ziyao.ideal.uua.service.app;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.uua.repository.redis.ApplicationRepositoryRedis;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;

/**
 * @author ziyao
 */
public class RedisRegisteredAppService extends AbstractRegisteredAppService {

    private final ApplicationRepositoryRedis applicationRepositoryRedis;

    public RedisRegisteredAppService(ApplicationRepositoryRedis applicationRepositoryRedis) {
        super();
        this.applicationRepositoryRedis = applicationRepositoryRedis;
    }

    @Override
    public void save(RegisteredApp registeredApp) {
        Assert.notNull(registeredApp, "registeredApp must not be null");
        applicationRepositoryRedis.save(this.toEntity(registeredApp));
    }

    @Override
    public RegisteredApp findById(Long appId) {
        Assert.notNull(appId, "appId must not be null");
        return applicationRepositoryRedis.findById(appId).map(this::toObject).orElse(null);
    }


    @Override
    public Model model() {
        return Model.redis;
    }
}
