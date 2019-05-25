package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.BlogPost;


@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends JpaRepository<BlogPost, String> {


}