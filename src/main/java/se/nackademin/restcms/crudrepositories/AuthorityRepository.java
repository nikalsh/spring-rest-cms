package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import se.nackademin.restcms.entities.Authority;
import se.nackademin.restcms.entities.AuthorityType;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

//	AuthorityType.ROLE_ADMIN

}
