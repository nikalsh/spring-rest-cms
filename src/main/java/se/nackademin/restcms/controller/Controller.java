package se.nackademin.restcms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;

@RestController
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
