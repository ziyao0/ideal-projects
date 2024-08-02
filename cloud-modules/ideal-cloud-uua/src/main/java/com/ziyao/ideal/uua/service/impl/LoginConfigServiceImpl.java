package com.ziyao.ideal.uua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.uua.repository.jpa.LoginConfigRepositoryJpa;
import com.ziyao.ideal.uua.repository.mapper.LoginConfigMapper;
import com.ziyao.ideal.uua.repository.redis.LoginConfigRepositoryRedis;
import com.ziyao.ideal.uua.service.LoginConfigService;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 登录配置表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class LoginConfigServiceImpl extends JapServiceImpl<LoginConfigRepositoryJpa, LoginConfig, Long> implements LoginConfigService {


    private static final String DEFAULT_LOGIN_METHOD = "PASSWD";

    private final LoginConfigMapper loginConfigMapper;
    private final LoginConfigRepositoryJpa loginConfigRepositoryJpa;
    private final LoginConfigRepositoryRedis loginConfigRepositoryRedis;


    private final Cache<String, LoginConfig> loginConfigCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(30, TimeUnit.DAYS)
            .build();

    @Override
    public Page<LoginConfig> page(Page<LoginConfig> page, LoginConfigDTO loginConfigDTO) {
        LambdaQueryWrapper<LoginConfig> wrapper = loginConfigDTO.initWrapper();
        // to do 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return loginConfigMapper.selectPage(page, wrapper);
    }

    @Override
    public LoginConfig getAccountPasswordLoginConfig() {

        Optional<LoginConfig> optional = Optional.ofNullable(loginConfigCache.getIfPresent(DEFAULT_LOGIN_METHOD));

        if (optional.isPresent()) {
            return optional.get();
        } else {
            Optional<LoginConfig> optionalByRedis = loginConfigRepositoryRedis.findById(DEFAULT_LOGIN_METHOD);
            if (optionalByRedis.isPresent()) {
                loginConfigCache.put(DEFAULT_LOGIN_METHOD, optionalByRedis.get());
                return optionalByRedis.get();
            } else {
                Optional<LoginConfig> optionalByJpa = loginConfigRepositoryJpa.findByLoginMethod(DEFAULT_LOGIN_METHOD);
                if (optionalByJpa.isPresent()) {
                    loginConfigCache.put(DEFAULT_LOGIN_METHOD, optionalByJpa.get());
                    loginConfigRepositoryRedis.save(optionalByJpa.get());
                    return optionalByJpa.get();
                }
            }
        }
        throw new ServiceException(400, "未获取到登录配置");
    }
}
