package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ziyao
 */
@Repository
public interface AuthorizationRepositoryJpa extends JpaRepository<Authorization, Integer> {

    Optional<Authorization> findByState(Integer state);

    Optional<Authorization> findByAuthorizationCodeValue(String authorizationCode);

    Optional<Authorization> findByAccessTokenValue(String accessToken);

    Optional<Authorization> findByRefreshTokenValue(String refreshToken);
}
