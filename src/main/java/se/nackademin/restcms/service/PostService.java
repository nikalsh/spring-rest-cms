package se.nackademin.restcms.service;

import se.nackademin.restcms.Entities.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostService {

List<Post> findAll ();
Optional<Post> findById (Long id);
void deleteById (Long id);
Post save (Post post);

}
