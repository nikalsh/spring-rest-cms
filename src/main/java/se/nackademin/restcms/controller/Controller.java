package se.nackademin.restcms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;

import java.security.Principal;

@RestController
@RequestMapping(value = {"/api/hello"}
        ,produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class Controller {


    private final
    BlogAdminRepository blogAdminRepository;

    @Autowired
    public Controller(BlogAdminRepository blogAdminRepository) {
        this.blogAdminRepository = blogAdminRepository;
    }

    @GetMapping("/test")
    public String result() {
        return "endpoint exposed";
    }



}
