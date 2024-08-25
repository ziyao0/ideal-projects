package ${package.ServiceImpl};

import ${package.RepositoryJpa}.${table.repositoryName};
import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 */
@Service
@RequiredArgsConstructor
public class ${table.serviceImplName} extends
    ${superServiceImplClass}< ${entity}RepositoryJpa, ${entity},${table.idPropertyType}> implements ${table.serviceName} {

    private final ${table.repositoryName} ${table.repositoryName?uncap_first};
}