package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.uua.repository.jpa.GlobalLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uua.service.GlobalLoginRestrictionService;
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
    JapServiceImpl< GlobalLoginRestrictionRepositoryJpa, GlobalLoginRestriction,Integer> implements GlobalLoginRestrictionService {

    private final GlobalLoginRestrictionRepositoryJpa globalLoginRestrictionRepositoryJpa;

}
