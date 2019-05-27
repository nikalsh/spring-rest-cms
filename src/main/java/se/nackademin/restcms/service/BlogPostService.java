package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.BlogPost;

public interface BlogPostService {

    BlogPost storePost (String file, String id);
    BlogPost getPost (String fileId);

}
