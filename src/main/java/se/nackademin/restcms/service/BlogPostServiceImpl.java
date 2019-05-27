package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogPostRepository;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.exception.MyFileNotFoundException;
import se.nackademin.restcms.security.UserDetailsImpl;


@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public BlogPost storePost(String file) {

        //get the current authenticated user
        UserDetailsImpl user =  (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogAdmin blogAdmin = user.getUser();

        //check the current users blog id
        user.getUser().getBlog().getBlogAdmin().getId();

        //decide if they can create a blogpost
        //...

        BlogPost blogPost = new BlogPost(file);

        blogPost.setBlog(blogRepository.getOne(blogAdmin.getBlog().getBlogAdmin().getId()));

        return blogPostRepository.save(blogPost);

    }

    @Override
    public BlogPost getPost(String fileId) {

        return blogPostRepository.findById(fileId)
                .orElseThrow (() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
