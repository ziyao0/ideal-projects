package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.Application;
import com.ziyao.ideal.uaa.service.ApplicationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
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
    JapServiceImpl< ApplicationRepositoryJpa, Application,Integer> implements ApplicationService {

    private final ApplicationRepositoryJpa applicationRepositoryJpa;

}
