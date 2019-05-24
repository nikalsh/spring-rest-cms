package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.Entities.ImageFile;
import se.nackademin.restcms.Entities.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Post storePost (String file);
    Post getPost (String fileId);

}
