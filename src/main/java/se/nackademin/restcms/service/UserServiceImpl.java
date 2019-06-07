package se.nackademin.restcms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.User;

import java.io.IOException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, ImageFileStorageService imageFileStorageService, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
    }


    @Override
    public User saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            user.getRoles().add(authorityRepository.getOne(1));
        }
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User updatePassword(String password) {
        User user = userRepository.findById(getCurrentUser().getId()).get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return getCurrentUser();
    }

    @Override
    public User registerUser(String email, String username,String blogname, String password, String profile, MultipartFile file){
        User user = new User();
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setActive(true);
        blog.setBlogName(blogname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setProfile(profile);
        try {
            user.setPhoto(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setBlog(blog);
        user.getRoles().add(authorityRepository.getOne(1));
        user.setEnabled(true);
        userRepository.save(user);
        blogRepository.save(blog);
        return user;
    }



}
