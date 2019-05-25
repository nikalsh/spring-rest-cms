package se.nackademin.restcms.CrudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import se.nackademin.restcms.Entities.BlogAdmin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "blogadmin", path = "blogadmin")
public interface BlogAdminRepository extends JpaRepository<BlogAdmin, Long> {

    @PostAuthorize("hasRole('ROLE_USER')")
    List<BlogAdmin> findByEmail(@Param("email") String email);

    BlogAdmin findByName(@Param("name") String name);

}
