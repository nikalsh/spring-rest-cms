package se.nackademin.restcms.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.service.BlogPostService;
import se.nackademin.restcms.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class BlogPostController {

    private final UserService blogAdminService;

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(UserService blogAdminService, BlogPostService blogPostService) {
        this.blogAdminService = blogAdminService;
        this.blogPostService = blogPostService;
    }

    @PostMapping(path = "/uploadPost")
    public ResponseEntity<String> uploadPost(@RequestParam("file") String file, @RequestParam("id") String postId) {

        BlogPost blogPost = blogPostService.storePost(file, postId);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getId());

    }

    @GetMapping(value = "/downloadPost/{postId}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPostById(@PathVariable String postId) {
        BlogPost blogPost = blogPostService.getPost(postId);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getPostData());
    }

    @GetMapping(value = "/myBlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BlogPost>> getBlogForUser(Authentication authentication) {
        User user=(User)authentication.getPrincipal();
        List<BlogPost> allPostsForBlog = blogPostService.getAllPostsForBlog(user.getBlog().getId());
        return new ResponseEntity<>(allPostsForBlog, HttpStatus.OK);
    }

    @GetMapping(value = "/postsByBlogId/{id}")
    public ResponseEntity<List<BlogPost>> getBlogPostsForBlogId(@PathVariable Long id) {
        List<BlogPost> allPostsForBlogId = blogPostService.getAllPostsForBlog(id);
        return new ResponseEntity<>(allPostsForBlogId, HttpStatus.OK);
    }

    @GetMapping(value = "/postsByBlogName/{name}")
    public ResponseEntity<List<BlogPost>> getBlogPostsForBlogId(@PathVariable String name) {
        List<BlogPost> allPostsForBlogId = blogPostService.getAllPostsForBlog(name);
        return new ResponseEntity<>(allPostsForBlogId, HttpStatus.OK);
    }

}