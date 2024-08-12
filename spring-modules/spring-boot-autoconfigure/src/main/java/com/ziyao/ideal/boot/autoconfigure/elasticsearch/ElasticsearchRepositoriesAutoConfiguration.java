package com.ziyao.ideal.boot.autoconfigure.elasticsearch;

import co.elastic.clients.elasticsearch.nodes.Client;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * org.elasticsearch.bootstrap.Bootstrap
 *
 * @author ziyao
 */
@AutoConfiguration
@ConditionalOnClass({Client.class, ElasticsearchRepository.class})
@ConditionalOnProperty(prefix = "spring.data.elasticsearch.repositories", name = "enabled", havingValue = "true",
        matchIfMissing = true)
@Import(ElasticsearchRepositoriesRegistrar.class)
public class ElasticsearchRepositoriesAutoConfiguration {
}
