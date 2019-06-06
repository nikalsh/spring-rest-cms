package se.nackademin.restcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.Authority;
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

    @Autowired
    public BlogController(final BlogServiceImpl blogService) {
        this.blogService = blogService;
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

}

