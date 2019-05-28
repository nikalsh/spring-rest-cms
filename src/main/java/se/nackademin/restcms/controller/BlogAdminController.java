package se.nackademin.restcms.controller;

import org.springframework.web.bind.annotation.RestController;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;

@RestController
public class BlogAdminController {

    private final BlogAdminRepository blogAdminRepository;

    public BlogAdminController(final BlogAdminRepository blogAdminRepository) {
        this.blogAdminRepository = blogAdminRepository;
    }





}
