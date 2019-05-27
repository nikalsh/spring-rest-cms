package se.nackademin.restcms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.nackademin.restcms.CKResponse;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.service.BlogPostService;

@RestController
public class BlogPostController {

    private static final Logger logger = LoggerFactory.getLogger(BlogPostController.class);

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/uploadPost")
    public ResponseEntity<CKResponse> uploadImage(@RequestParam("file") String file,@RequestParam("id") String postId) {

        BlogPost blogPost = blogPostService.storePost(file,postId);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadPost/")
//                .path(blogPost.getId())
//                .toUriString();
        return new ResponseEntity<>(new CKResponse(blogPost.getId()), HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value="/downloadPost/{postId}",produces=MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> downloadFile(@PathVariable String postId) {
        // Load file from database
        BlogPost blogPost = blogPostService.getPost(postId);
        System.out.println(blogPost.getPostData());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body( blogPost.getPostData());
    }

}