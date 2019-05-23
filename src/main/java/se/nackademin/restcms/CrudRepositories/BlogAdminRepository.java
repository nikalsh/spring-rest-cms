package se.nackademin.restcms.CrudRepositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import se.nackademin.restcms.Entities.BlogAdmin;

@RepositoryRestResource(collectionResourceRel = "blogadmin", path = "blogadmin")
public interface BlogAdminRepository extends CrudRepository<BlogAdmin, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<BlogAdmin> findByEmail(@Param("email") String email);




}
