package se.nackademin.restcms.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.User;
//import se.nackademin.restcms.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    // Replaces @AutoWired
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // Needs implementation
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    // Needs implementation
    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.empty();
    }

    // Needs implementation
    @Override
    public void deleteById(Long id) {

    }

    // Needs implementation
    @Override
    public Blog save(Blog blog) {
        return null;
    }

    @Override
    public Optional<Blog> getCurrentUsersBlog() {
       // UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      //  return blogRepository.findById(user.getUser().getBlog().getId());
        return null;
    }
}
