package se.nackademin.restcms.service;

import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.entities.Blog;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
	
	// Replaces @AutoWired
	private final BlogRepository blogRepository;
public BlogServiceImpl (BlogRepository blogRepository) {
	this.blogRepository = blogRepository;
}

// Needs implementation
@Override
public List<Blog> findAll () {
	return null;
}

// Needs implementation
@Override
public Optional<Blog> findById (Long id) {
	return Optional.empty ();
}

// Needs implementation
@Override
public void deleteById (Long id) {

}
// Needs implementation
@Override
public Blog save (Blog blog) {
	return null;
}
}
