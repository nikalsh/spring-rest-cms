package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.User;

public interface UserService {

    User saveUser(User user);

    User getCurrentUser();

    User updatePassword(String password);

    User registerUser(String email, String username,String blogname, String password,String profile, MultipartFile file);
}
