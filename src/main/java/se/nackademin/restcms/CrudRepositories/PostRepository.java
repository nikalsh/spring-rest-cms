package se.nackademin.restcms.CrudRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.Entities.Post;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends CrudRepository<Post, Long> {



}