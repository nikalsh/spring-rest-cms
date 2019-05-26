package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import se.nackademin.restcms.entities.BlogAdmin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "blogadmin", path = "blogadmin")
public interface BlogAdminRepository extends JpaRepository<BlogAdmin, Long> {

    List<BlogAdmin> findByEmail(@Param("email") String email);

    BlogAdmin findByName(@Param("name") String name);

}
