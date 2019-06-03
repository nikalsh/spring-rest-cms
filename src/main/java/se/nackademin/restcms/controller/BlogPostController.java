package se.nackademin.restcms.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.entities.CustomUserDetail;
import se.nackademin.restcms.service.BlogPostService;

import java.security.Principal;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/post")
public class BlogPostController {



    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping(path = "/uploadPost")
    public ResponseEntity<String> uploadPost(@RequestParam("file") String file, @RequestParam("id") String postId) {

        BlogPost blogPost = blogPostService.storePost(file, postId);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getId());

    }
    @PostMapping( path = "/hello")
    public ResponseEntity<String> greetings() {

//        return new Welcome(name + " (" + principal.getName() + ")");
       return  ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body("hi");
    }

    @PostMapping(path = "/test")
    public String test() {
        return "HELLO";
    }

    @GetMapping(value = "/downloadPost/{postId}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPostById(@PathVariable String postId) {
        // Load file from database
        BlogPost blogPost = blogPostService.getPost(postId);
        System.out.println(blogPost.getPostData());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getPostData());
    }

    @GetMapping(value = "/downloadPosts/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<List<BlogPost>> getPostsByBlog(@PathVariable String blogId) {

        List<BlogPost> allPostsForBlog = blogPostService.getAllPostsForBlog(Long.parseLong(blogId));
        return new ResponseEntity<>(allPostsForBlog, HttpStatus.OK);
    }

}