package se.nackademin.restcms.CrudRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.Entities.Admin;

@RepositoryRestResource(collectionResourceRel = "admin", path = "admin")
public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
    
    List<Admin> findByEmail(@Param("email") String email);

}
