package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.Nullable;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogPost;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface BlogPostRepository extends JpaRepository<BlogPost, String> {

    @Nullable
    List<BlogPost> findByBlogOrderByDateCreatedDesc(Blog blog);


}