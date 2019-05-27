package se.nackademin.restcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.service.BlogAdminServiceImpl;

@BasePathAwareController
public class BlogAdminController {

    private final BlogAdminRepository blogAdminRepository;

    public BlogAdminController(final BlogAdminRepository blogAdminRepository) {
        this.blogAdminRepository = blogAdminRepository;
    }





}
