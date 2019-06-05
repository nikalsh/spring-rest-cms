package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogPostRepository;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.exception.MyFileNotFoundException;
//import se.nackademin.restcms.security.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogRepository blogRepository;
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostServiceImpl(BlogRepository blogRepository, BlogPostRepository blogPostRepository) {
        this.blogRepository = blogRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<String> getAllPostIdsForBlog(Long blogId) {
        List<String> l=blogPostRepository
                .findByBlogOrderByDateCreatedDesc(blogRepository.getOne(blogId))
                .stream()
                .map(BlogPost::getId)
                .collect(Collectors.toList());
        return l;
    }

    @Override
    public List<BlogPost> getAllPostsForBlog(String blogName){
        return blogPostRepository.findAll()
                .stream()
                .filter(e -> e.getBlog().getBlogName().equals(blogName))
                .collect(Collectors.toList());
    }
    @Override
    public List<BlogPost> getAllPostsForBlog(Long blogId) {
        return blogPostRepository
                .findByBlogOrderByDateCreatedDesc(blogRepository.getOne(blogId))
                ;
    }

    @Override
    public BlogPost storePost(String file, String postId) {
        //get the current authenticated user
//        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Blog blog = user.getUser().getBlog();

        //decide if they can create a blogpost
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(postId);
        BlogPost blogPost;
        if (blogPostOptional.isPresent()) {
            blogPost = blogPostOptional.get();
            blogPost.setPostData(file);
        } else {
            blogPost = new BlogPost(
                    // blogRepository.getOne(user.getUser().getBlog().getUser().getId()),
                    blogRepository.getOne(4L),
                   // blog,
                    file
            );
        }
        return blogPostRepository.save(blogPost);
    }


    @Override
    public BlogPost getPost(String fileId) {

        return blogPostRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
