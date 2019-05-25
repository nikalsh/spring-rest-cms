package se.nackademin.restcms.service;

import se.nackademin.restcms.Entities.BlogPost;

public interface BlogPostService {

    BlogPost storePost (String file);
    BlogPost getPost (String fileId);

}
