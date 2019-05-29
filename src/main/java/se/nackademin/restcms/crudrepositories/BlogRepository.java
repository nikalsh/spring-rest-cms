package se.nackademin.restcms.crudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.Blog;

//@RepositoryRestResource(collectionResourceRel = "blog", path = "blog")
public interface BlogRepository extends JpaRepository<Blog, Long> {




}