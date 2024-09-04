package com.ziyao.ideal.config.repository.jpa;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 配置表 持久化接口
 * </p>
 *
 * @author ziyao
 */
@Repository
public interface ConfigRepositoryJpa extends JpaRepository<Config, Integer> {


    Optional<Config> findByDataIdAndGroup(String dataId, String group);

    void deleteByDataIdAndGroup(String dataId, String group);

    @Query("select new com.ziyao.ideal.config.domain.entity.Configuration(c.dataId,c.group,c.configType,ci.configId,ci.propertyKey,ci.propertyValue,ci.propertyType,ci.configLevel) " +
            "from config c join config_property ci on c.id = ci.configId " +
            "where c.dataId = :dataId and c.group = :group")
    List<Configuration> findConfigurationByDataIdAndGroup(@Param("dataId") String dataId, @Param("group") String group);


    @Query("select new com.ziyao.ideal.config.domain.entity.Configuration(c.dataId,c.group,c.configType,ci.configId,ci.propertyKey,ci.propertyValue,ci.propertyType,ci.configLevel) " +
            "from config c join config_property ci on c.id = ci.configId ")
    List<Configuration> findConfiguration();

}
