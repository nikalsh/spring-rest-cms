package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.service.BlogAdminService;
import se.nackademin.restcms.service.BlogAdminServiceImpl;
import se.nackademin.restcms.service.ImageFileStorageService;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/blogadmin")
public class BlogAdminController {


    private final BlogAdminService blogAdminService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ImageFileStorageService imageFileStorageService;


    @Autowired
    public BlogAdminController(final BlogAdminServiceImpl blogAdminService) {
        this.blogAdminService = blogAdminService;
    }

    @PostMapping
    public BlogAdmin newBlogAdmin(@RequestBody BlogAdmin blogAdmin) {
        return blogAdminService.saveBlogAdmin(blogAdmin);
    }

    @PostMapping(path = "/me")
    public BlogAdmin me() {
        return blogAdminService.getCurrentBlogAdmin();
    }

    @RequestMapping("/user")
    public Principal home(Principal user) {
        return user;
    }

    @PutMapping(path = "/update_password")
    public BlogAdmin newPassword(@RequestBody Map<String, Object> password) {
        log.info("NEW PASSWORD: " + password.get("password"));
        return blogAdminService.updatePassword(password.get("password").toString());
    }


    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/registerUser")
    public BlogAdmin registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) {

        BlogAdmin blogAdmin = new BlogAdmin();


        blogAdmin.setEmail(email);
        blogAdmin.setName(name);


        blogAdmin.setPassword(passwordEncoder.encode(password));


        blogAdminService.saveBlogAdmin(blogAdmin);
        ImageFile imageFile = imageFileStorageService.storeImageFile(file, blogAdmin);

        return blogAdmin;
        // return new BlogAdmin ();
        // return new UserCreatedResponse (blogAdmin.getId (), blogAdmin.getEmail (), blogAdmin.getName ());
    }


}
