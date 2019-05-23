package se.nackademin.restcms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.CrudRepositories.BlogAdminRepository;
import se.nackademin.restcms.Entities.BlogAdmin;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    BlogAdminRepository blogAdminRepository;

    @GetMapping("/test")
    public String result() {
        return "endpoint exposed";
    }



}
