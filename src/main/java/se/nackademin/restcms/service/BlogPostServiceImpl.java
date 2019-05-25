package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.PostRepository;
import se.nackademin.restcms.entities.BlogPost;
import se.nackademin.restcms.exception.MyFileNotFoundException;

<<<<<<<HEAD:src/main/java/se/nackademin/restcms/service/BlogPostServiceImpl.java
        =======
        >>>>>>>535d15005d8f32888694fdf9eac5bac6f50e3f97:src/main/java/se/nackademin/restcms/service/PostServiceImpl.java

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public BlogPost storePost(String file) {


            BlogPost blogPost = new BlogPost( file);

            blogPost.setBlog (blogRepository.getOne ((long) 2));

            return postRepository.save (blogPost);

    }

    @Override
    public BlogPost getPost(String fileId) {

        return postRepository.findById (fileId)
                .orElseThrow (() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
