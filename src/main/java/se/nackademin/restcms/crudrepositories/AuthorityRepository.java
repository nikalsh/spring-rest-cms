package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.nackademin.restcms.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
