package se.nackademin.restcms.crudrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.Nullable;
import se.nackademin.restcms.entities.Blog;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByBlogName(String name);
   List<Blog> findFirst4By();

}