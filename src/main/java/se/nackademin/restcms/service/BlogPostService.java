package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface BlogPostService {

    BlogPost storePost (String file, String id);
    BlogPost getPost (String fileId);
    List<BlogPost> getAllPostsForBlog(Long blogId);
    List<String> getAllPostIdsForBlog(Long blogId);

}
