package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.BlogAdmin;

public interface BlogAdminService {

    BlogAdmin saveBlogAdmin(BlogAdmin blogAdmin);

    BlogAdmin getCurrentBlogAdmin();
}
