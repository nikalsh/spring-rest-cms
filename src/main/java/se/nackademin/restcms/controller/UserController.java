package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.*;
import se.nackademin.restcms.service.UserService;
import se.nackademin.restcms.service.UserServiceImpl;
import se.nackademin.restcms.service.ImageFileStorageService;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService blogAdminService;

    private final PasswordEncoder passwordEncoder;

    private final ImageFileStorageService imageFileStorageService;


    @Autowired
    public UserController(final UserServiceImpl blogAdminService, PasswordEncoder passwordEncoder, ImageFileStorageService imageFileStorageService) {
        this.blogAdminService = blogAdminService;
        this.passwordEncoder = passwordEncoder;
        this.imageFileStorageService = imageFileStorageService;
    }

    @PostMapping
    public User newBlogAdmin(@RequestBody User user) {
        return blogAdminService.saveBlogAdmin(user);
    }

    @GetMapping(path = "/me")
    public User me() {
        return blogAdminService.getCurrentUser();
    }

    @RequestMapping("/getUser")
    public Principal home(Principal user) {
        return user;
    }

    @PutMapping(path = "/update_password")
    public User newPassword(@RequestBody Map<String, Object> password) {
        log.info("NEW PASSWORD: " + password.get("password"));
        return blogAdminService.updatePassword(password.get("password").toString());
    }


    @PostMapping("/registerUser")
    public User registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        Blog blog = new Blog();
        user.setBlog(blog);
        blogAdminService.saveBlogAdmin(user);
        imageFileStorageService.storeImageFile(file, user);

        return user;
    }


}
