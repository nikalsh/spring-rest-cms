package se.nackademin.restcms.controller;

import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.security.UserDetailsServiceImpl;
import se.nackademin.restcms.service.BlogAdminService;
import se.nackademin.restcms.service.BlogAdminServiceImpl;

@RestController
@RequestMapping("/blogadmin")
public class BlogAdminController {

    private final UserDetailsServiceImpl userDetailsService;
    private final BlogAdminService blogAdminService;

    public BlogAdminController(final UserDetailsServiceImpl userDetailsService,
                               final BlogAdminServiceImpl blogAdminService) {
        this.userDetailsService = userDetailsService;
        this.blogAdminService = blogAdminService;
    }


    @PostMapping
    public BlogAdmin newBlogAdmin(@RequestBody BlogAdmin blogAdmin) {

        return blogAdminService.saveBlogAdmin(blogAdmin);
    }

    @GetMapping(path = "/me")
    public BlogAdmin getBlogAdmin() {
        return blogAdminService.getCurrentBlogAdmin();
    }


//    @GetMapping
//    public BlogAdmin getBlogAdmin(@Request)




}
