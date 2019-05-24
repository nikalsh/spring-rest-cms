package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.CrudRepositories.BlogRepository;
import se.nackademin.restcms.CrudRepositories.PostRepository;
import se.nackademin.restcms.Entities.ImageFile;
import se.nackademin.restcms.Entities.Post;
import se.nackademin.restcms.exception.FileStorageException;
import se.nackademin.restcms.exception.MyFileNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post storePost(String file) {


            Post post = new Post ( file);

            post.setBlog (blogRepository.getOne ((long) 2));

            return postRepository.save (post);

    }

    @Override
    public Post getPost(String fileId) {

        return postRepository.findById (fileId)
                .orElseThrow (() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
