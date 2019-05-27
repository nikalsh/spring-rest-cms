package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.BlogPost;


@RepositoryRestResource(collectionResourceRel = "blogpost", path = "blogpost")
public interface BlogPostRepository extends JpaRepository<BlogPost, String> {



}