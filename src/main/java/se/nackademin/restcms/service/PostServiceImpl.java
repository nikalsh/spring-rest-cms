package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.CrudRepositories.PostRepository;
import se.nackademin.restcms.Entities.Post;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

	// Replaces @AutoWired
	private final PostRepository postRepository;
public PostServiceImpl (PostRepository postRepository) {
	this.postRepository = postRepository;
}

// Needs implementation
@Override
public List<Post> findAll () {
	return null;
}

// Needs implementation
@Override
public Optional<Post> findById (Long id) {
	return Optional.empty ();
}

// Needs implementation
@Override
public void deleteById (Long id) {
}

// Needs implementation
@Override
public Post save (Post post) {
	return null;
}
}
