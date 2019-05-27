package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.PostRepository;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.exception.MyFileNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public BlogPost storePost(String file, String postId) {
        Optional<BlogPost> blogPostOptional = postRepository.findById(postId);
        BlogPost blogPost;
        if (blogPostOptional.isPresent()) {
            blogPost = blogPostOptional.get();
            blogPost.setPostData(file);
            blogPost.setLastUpdated(LocalDateTime.now());
        } else {
            blogPost = new BlogPost(blogRepository.getOne((long) 2), LocalDateTime.now(), file);
        }
        blogPost.setPostData(file);
        return postRepository.save(blogPost);

    }

    @Override
    public BlogPost getPost(String fileId) {

        return postRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
