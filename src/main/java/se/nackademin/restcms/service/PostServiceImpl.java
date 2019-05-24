package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.PostRepository;
import se.nackademin.restcms.entities.Post;
import se.nackademin.restcms.exception.MyFileNotFoundException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post storePost(String file) {


            Post post = new Post ( file);

            post.setBlog (blogRepository.getOne ((long) 2));

            return postRepository.save (post);

    }

    @Override
    public Post getPost(String fileId) {

        return postRepository.findById (fileId)
                .orElseThrow (() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
