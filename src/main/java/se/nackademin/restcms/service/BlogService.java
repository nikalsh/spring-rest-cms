package se.nackademin.restcms.service;

import se.nackademin.restcms.Entities.Blog;
import se.nackademin.restcms.Entities.Post;

import java.util.List;
import java.util.Optional;

public interface BlogService {

List<Blog> findAll ();

Optional<Blog> findById (Long id);

void deleteById (Long id);

Blog save (Blog blog);

}
