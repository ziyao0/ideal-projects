package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.GlobalLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uaa.service.GlobalLoginRestrictionService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 全局登录限制表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class GlobalLoginRestrictionServiceImpl extends
        JpaServiceImpl< GlobalLoginRestrictionRepositoryJpa, GlobalLoginRestriction,Integer> implements GlobalLoginRestrictionService {

    private final GlobalLoginRestrictionRepositoryJpa globalLoginRestrictionRepositoryJpa;

}
