package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.service.BlogAdminService;
import se.nackademin.restcms.service.BlogAdminServiceImpl;

import java.security.Principal;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/blogadmin")
public class BlogAdminController {


    private final BlogAdminService blogAdminService;

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
}
