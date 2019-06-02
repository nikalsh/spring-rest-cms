package se.nackademin.restcms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.service.BlogPostService;
import se.nackademin.restcms.service.BlogService;

import java.util.List;

@RestController
public class BlogPostController {

    private static final Logger logger = LoggerFactory.getLogger(BlogPostController.class);

    private final BlogPostService blogPostService;
    private final BlogService blogService;

    public BlogPostController(BlogPostService blogPostService, BlogService blogService) {
        this.blogPostService = blogPostService;
        this.blogService = blogService;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/uploadPost")
    public ResponseEntity<String> uploadPost(@RequestParam("file") String file, @RequestParam("id") String postId) {

        BlogPost blogPost = blogPostService.storePost(file, postId);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadPost/")
//                .path(blogPost.getId())
//                .toUriString();
        //return new ResponseEntity<>(new CKResponse(blogPost.getId()), HttpStatus.OK);
      return  ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getId());

    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/downloadPost/{postId}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> downloadFile(@PathVariable String postId) {
        // Load file from database
        BlogPost blogPost = blogPostService.getPost(postId);
        System.out.println(blogPost.getPostData());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(blogPost.getPostData());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/downloadPosts/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<BlogPost>> getPostsByBlog(@PathVariable String blogId) {
        List<BlogPost> allPostsForBlog = blogPostService.getAllPostsForBlog(Long.parseLong(blogId));
        return new ResponseEntity<>(allPostsForBlog, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/downloadPostIds/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<String>> getPostsByBlogId(@PathVariable String blogId) {
        List<String> allPostsForBlog = blogPostService.getAllPostIdsForBlog(Long.parseLong(blogId));
        return new ResponseEntity<>(allPostsForBlog, HttpStatus.OK);
    }

}