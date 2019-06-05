package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.service.BlogService;
import se.nackademin.restcms.service.UserService;
import se.nackademin.restcms.service.UserServiceImpl;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final BlogService blogService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserServiceImpl userService, BlogService blogService, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder,  BlogRepository blogRepository, UserRepository userRepository) {
        this.userService = userService;
        this.blogService = blogService;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public User newBlogAdmin(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/me")
    public User me() {
        return userService.getCurrentUser();
    }

    @RequestMapping("/getUser")
    public Principal home(Principal user) {
        return user;
    }

    @PutMapping(path = "/update_password")
    public User newPassword(@RequestBody Map<String, Object> password) {
        log.info("NEW PASSWORD: " + password.get("password"));
        return userService.updatePassword(password.get("password").toString());
    }


    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam("file") MultipartFile file) throws IOException {
        userService.registerUser(email,username,password,file);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
