package se.nackademin.restcms.crudrepos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.Blog;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "blog", path = "blog")
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {



}
