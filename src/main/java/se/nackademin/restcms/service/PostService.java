package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.Post;

public interface PostService {

    Post storePost (String file);
    Post getPost (String fileId);

}
