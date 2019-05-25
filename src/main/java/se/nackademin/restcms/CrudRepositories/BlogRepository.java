package se.nackademin.restcms.CrudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.Entities.Blog;

@RepositoryRestResource(collectionResourceRel = "blog", path = "blog")
public interface BlogRepository extends JpaRepository<Blog, Long> {

}