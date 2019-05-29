package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.BlogPost;


@RepositoryRestResource(exported = false)
public interface BlogPostRepository extends JpaRepository<BlogPost, String> {



}