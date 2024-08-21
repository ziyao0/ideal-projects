package com.ziyao.ideal.boot.autoconfigure.elasticsearch;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * org.elasticsearch.bootstrap.Bootstrap
 *
 * @author ziyao
 */
@Configuration
@ConditionalOnClass({ElasticsearchRepository.class})
@ConditionalOnProperty(prefix = "spring.data.elasticsearch.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
@Import(ElasticsearchRepositoriesRegistrar.class)
public class ElasticsearchRepositoriesAutoConfiguration {
}
