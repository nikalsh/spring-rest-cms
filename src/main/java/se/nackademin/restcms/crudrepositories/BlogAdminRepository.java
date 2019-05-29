package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.BlogAdmin;

@RepositoryRestResource(exported = false)
public interface BlogAdminRepository extends JpaRepository<BlogAdmin, Long> {




    BlogAdmin findByEmail(@Param("email") String email);

    BlogAdmin findByName(@Param("name") String name);


}
