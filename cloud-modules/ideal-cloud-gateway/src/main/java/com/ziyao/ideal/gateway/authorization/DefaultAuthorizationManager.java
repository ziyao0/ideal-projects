package com.ziyao.ideal.gateway.authorization;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 授权管理器，授权核心逻辑。实现{@link AuthorizationManager}授权管理处理授权核心
 * 逻辑，授权是通过委托的设计模式实现多条件授权过滤的，授权方案统一由
 * {@link AuthorizationProvider}提供.
 * <p>
 * {@link com.ziyao.ideal.gateway.authorization.token.Authorization}说明：
 * 授权统一入口，由各个授权方案去处理并返回{@link com.ziyao.ideal.gateway.authorization.token.Authorization}授权结果，采用所有
 * 条件都满足之后，即最终返回的{@link com.ziyao.ideal.gateway.authorization.token.Authorization}不为空并且{@link com.ziyao.ideal.gateway.authorization.token.Authorization#isAuthorized()}
 * 为true则表示授权成功.
 * </p>
 *
 * @author ziyao zhang
 */
@Getter
public class DefaultAuthorizationManager implements AuthorizationManager {

    private final Map<String, AuthorizationProvider> authorizerMap;


    public DefaultAuthorizationManager(List<AuthorizationProvider> authorizationProviders) {
        // 初始化所有鉴权提供者
        this.authorizerMap = authorizationProviders.stream().collect(
                Collectors.toMap(null, Function.identity()));
    }

    @Override
    public AuthorizationProvider getAuthorization(String name) {
        AuthorizationProvider authorizationProvider = getAuthorizerMap().get(name);
        if (Objects.isNull(authorizationProvider)) {
//            throw Exceptions.createIllegalAccessException("未获取到对应的授权方，name：" + name);
        }
        return null;
    }
}
