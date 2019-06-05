package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.*;
import se.nackademin.restcms.service.BlogService;
import se.nackademin.restcms.service.UserService;
import se.nackademin.restcms.service.UserServiceImpl;
import se.nackademin.restcms.service.ImageFileStorageService;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final BlogService blogService;

    private final PasswordEncoder passwordEncoder;

    private final ImageFileStorageService imageFileStorageService;


    @Autowired
    public UserController(final UserServiceImpl userService, BlogService blogService, PasswordEncoder passwordEncoder, ImageFileStorageService imageFileStorageService) {
        this.userService = userService;
        this.blogService = blogService;
        this.passwordEncoder = passwordEncoder;
        this.imageFileStorageService = imageFileStorageService;
    }

    @PostMapping
    public User newBlogAdmin(@RequestBody User user) {
        return userService.saveBlogAdmin(user);
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
    public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) throws IOException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoto(file.getBytes());
        Blog blog = new Blog();
        user.setBlog(blog);
        user.setEnabled(true);
        blogService.save(blog);
        userService.saveBlogAdmin(user);

        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
