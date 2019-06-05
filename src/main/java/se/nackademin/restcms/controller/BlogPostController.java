package se.nackademin.restcms.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.service.UserService;
import se.nackademin.restcms.service.BlogPostService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

    @GetMapping(value = "/downloadPosts/myBlog", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<BlogPost>> getBlogForUser(Authentication authentication) {
        User user=(User)authentication.getPrincipal();
        List<BlogPost> allPostsForBlog = blogPostService.getAllPostsForBlog(user.getBlog().getId());
        return new ResponseEntity<>(allPostsForBlog, HttpStatus.OK);
    }

}