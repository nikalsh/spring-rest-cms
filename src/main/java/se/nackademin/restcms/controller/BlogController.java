package se.nackademin.restcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.service.BlogService;
import se.nackademin.restcms.service.BlogServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogController( BlogService blogService, BlogRepository blogRepository, UserRepository userRepository) {
        this.blogService = blogService;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> allBlogs = blogService.findAll();
        return new ResponseEntity<>(allBlogs, HttpStatus.OK);
    }

    @GetMapping(path = "/allnames")
    public @ResponseBody
    ResponseEntity<List<String>> getAllBlogNames() {
        List<String> allBlogs = blogService.findAll().stream().map(Blog::getBlogName).collect(Collectors.toList());

        return new ResponseEntity<>(allBlogs, HttpStatus.OK);
    }
    @GetMapping(path = "/getFour")
    public @ResponseBody
    ResponseEntity<List<Blog>> getFour() {
        List<Blog> blogs = blogRepository.findFirst4By();

        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping(path = "/me")
    public Optional<Blog> me() {
        return blogService.getCurrentUsersBlog();
    }

    @GetMapping(path = "/isOwner/{blogName}")
    public @ResponseBody
    ResponseEntity<Boolean> isOwner(Authentication auth, @PathVariable String blogName) {
        boolean match = false;
        if (auth != null) {
            User user = (User) auth.getPrincipal();
            match = user.getBlog().getBlogName().equals(blogName);
        }
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @GetMapping(path = "/getBlog/{blogName}")
    public @ResponseBody
    ResponseEntity<Blog> getBlogByName(@PathVariable String blogName) {
        Blog blog = blogRepository.findByBlogName(blogName);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PutMapping(path = "/saveImage")
    public @ResponseBody
    ResponseEntity<Void> saveBlogImage(Authentication auth, @RequestParam("file") MultipartFile file) {
        User user = (User) auth.getPrincipal();
        Blog blog=user.getBlog();
        try {
            blog.setHeaderImage(file.getBytes());
            blogRepository.save(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}

