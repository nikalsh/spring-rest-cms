package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {




    User findByEmail(@Param("email") String email);

    User findByUsername(@Param("username") String username);


}
