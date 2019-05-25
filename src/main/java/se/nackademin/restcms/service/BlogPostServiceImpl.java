package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.CrudRepositories.BlogRepository;
import se.nackademin.restcms.CrudRepositories.PostRepository;
import se.nackademin.restcms.Entities.BlogPost;
import se.nackademin.restcms.exception.MyFileNotFoundException;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public BlogPost storePost(String file) {


            BlogPost blogPost = new BlogPost( file);

            blogPost.setBlog (blogRepository.getOne ((long) 2));

            return postRepository.save (blogPost);

    }

    @Override
    public BlogPost getPost(String fileId) {

        return postRepository.findById (fileId)
                .orElseThrow (() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
