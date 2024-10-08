package com.ziyao.ideal.uaa.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.uaa.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.uaa.repository.jpa.LoginConfigRepositoryJpa;
import com.ziyao.ideal.uaa.repository.redis.LoginConfigRepositoryRedis;
import com.ziyao.ideal.uaa.service.LoginConfigService;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class LoginConfigServiceImpl implements LoginConfigService {


    private static final String DEFAULT_LOGIN_METHOD = "USERNAME_PASSWORD";

    private final LoginConfigRepositoryJpa loginConfigRepositoryJpa;
    private final LoginConfigRepositoryRedis loginConfigRepositoryRedis;


    private final Cache<String, LoginConfig> loginConfigCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(30, TimeUnit.DAYS)
            .build();

    @Override
    public LoginConfig getAccountPasswordLoginConfig() {

        Optional<LoginConfig> optional = Optional.ofNullable(loginConfigCache.getIfPresent(DEFAULT_LOGIN_METHOD));

        if (optional.isPresent()) {
            return optional.get();
        } else {
//            Optional<LoginConfig> optionalByRedis = loginConfigRepositoryRedis.findById(DEFAULT_LOGIN_METHOD);
//            if (optionalByRedis.isPresent()) {
//                loginConfigCache.put(DEFAULT_LOGIN_METHOD, optionalByRedis.get());
//                return optionalByRedis.get();
//            } else {
            Optional<LoginConfig> optionalByJpa = loginConfigRepositoryJpa.findByLoginMethod(DEFAULT_LOGIN_METHOD);
            if (optionalByJpa.isPresent()) {
                loginConfigCache.put(DEFAULT_LOGIN_METHOD, optionalByJpa.get());
                loginConfigRepositoryRedis.save(DEFAULT_LOGIN_METHOD, optionalByJpa.get());
                return optionalByJpa.get();
            }
//            }
        }
        throw new ServiceException(400, "未获取到登录配置");
    }


    /**
     * 保存 登录配置表
     *
     * @param loginConfigDTO 待存储对象
     */
    @Override
    public void save(LoginConfigDTO loginConfigDTO) {
        loginConfigRepositoryJpa.save(loginConfigDTO.toEntity());
    }

    /**
     * 通过主键修改 登录配置表
     *
     * @param loginConfigDTO 待修改对象
     */
    @Override
    public void updateById(LoginConfigDTO loginConfigDTO) {
        loginConfigRepositoryJpa.save(loginConfigDTO.toEntity());
    }

    /**
     * 通过主键删除 登录配置表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Long id) {
        loginConfigRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 登录配置表
     *
     * @param loginConfigDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<LoginConfig> page(LoginConfigDTO loginConfigDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<LoginConfig> example = Example.of(loginConfigDTO.toEntity(), matcher);
        return loginConfigRepositoryJpa.findAll(example, pageable);
    }

}