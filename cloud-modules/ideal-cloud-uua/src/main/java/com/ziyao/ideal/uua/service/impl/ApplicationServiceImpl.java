package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.Application;
import com.ziyao.ideal.uua.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.ideal.uua.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用系统 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl extends
        JapServiceImpl<ApplicationRepositoryJpa, Application, Integer> implements ApplicationService {

    private final ApplicationRepositoryJpa applicationRepositoryJpa;

}
