package com.ziyao.ideal.uaa.service.app;

import com.ziyao.ideal.uaa.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;

/**
 * @author ziyao
 */
public class JpaRegisteredAppService extends AbstractRegisteredAppService {

    private final ApplicationRepositoryJpa applicationRepositoryJpa;

    public JpaRegisteredAppService(ApplicationRepositoryJpa applicationRepositoryJpa) {
        super();
        this.applicationRepositoryJpa = applicationRepositoryJpa;
    }

    @Override
    public void save(RegisteredApp registeredApp) {
        applicationRepositoryJpa.save(toEntity(registeredApp));
    }

    @Override
    public RegisteredApp findById(Integer appId) {
        return applicationRepositoryJpa.findById(appId).map(this::toObject).orElse(null);
    }

    @Override
    public Model model() {
        return Model.jpa;
    }


}
