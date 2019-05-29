package se.nackademin.restcms.crudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.nackademin.restcms.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
