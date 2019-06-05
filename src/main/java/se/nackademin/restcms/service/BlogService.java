package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.User;

import java.util.List;
import java.util.Optional;

public interface BlogService {

    List<Blog> findAll();

    Optional<Blog> findById(Long id);

    void deleteById(Long id);


    // Needs implementation
    Blog save(Blog blog);

    Optional<Blog> getCurrentUsersBlog();

}
