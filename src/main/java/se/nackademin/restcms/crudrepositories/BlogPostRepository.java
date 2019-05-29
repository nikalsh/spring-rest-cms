package se.nackademin.restcms.crudRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogPost;

import java.util.List;
import java.util.Set;


//@RepositoryRestResource(collectionResourceRel = "blogpost", path = "blogpost")
public interface BlogPostRepository extends JpaRepository<BlogPost, String> {

    List<BlogPost> findByBlogOrderByCreatedDesc(Blog blog);

}