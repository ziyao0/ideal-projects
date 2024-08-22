package com.ziyao.ideal.uaa.service.app;

import com.ziyao.ideal.security.oauth2.core.RegisteredApp;

import java.util.List;

/**
 * @author ziyao
 */
public class DelegatingRegisteredAppService implements RegisteredAppService {

    private RegisteredAppService redisRegisteredAppService;
    private RegisteredAppService memoryRegisteredAppService;
    private RegisteredAppService jpaRegisteredAppService;

    public DelegatingRegisteredAppService(List<RegisteredAppService> registeredAppServices) {
        for (RegisteredAppService registeredAppService : registeredAppServices) {
            switch (registeredAppService.model()) {
                case caffeine:
                    this.memoryRegisteredAppService = registeredAppService;
                    break;
                case redis:
                    this.redisRegisteredAppService = registeredAppService;
                    break;
                case jpa:
                    this.jpaRegisteredAppService = registeredAppService;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + registeredAppService.model());
            }
        }
    }


    @Override
    public void save(RegisteredApp registeredApp) {
        this.jpaRegisteredAppService.save(registeredApp);
        registeredApp = this.jpaRegisteredAppService.findById(registeredApp.getAppId());

        this.redisRegisteredAppService.save(registeredApp);
        this.memoryRegisteredAppService.save(registeredApp);
    }

    @Override
    public RegisteredApp findById(Integer appId) {
        RegisteredApp registeredApp = this.memoryRegisteredAppService.findById(appId);

        if (registeredApp == null) {
            registeredApp = this.redisRegisteredAppService.findById(appId);
            if (registeredApp == null) {
                registeredApp = this.jpaRegisteredAppService.findById(appId);
                if (registeredApp != null) {
                    this.redisRegisteredAppService.save(registeredApp);
                    this.memoryRegisteredAppService.save(registeredApp);
                }
            } else {
                this.memoryRegisteredAppService.save(registeredApp);
            }
        }

        return registeredApp;
    }
}
