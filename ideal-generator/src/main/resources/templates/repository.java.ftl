package ${package.RepositoryJpa};

import ${package.Entity}.${entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ${author}
*/
@Repository
public interface ${table.repositoryName} extends JpaRepository<${entity}, ${table.idPropertyType}> {
}
