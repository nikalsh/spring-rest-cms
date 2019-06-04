package se.nackademin.restcms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.AuthorityType;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.entities.ImageFile;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private final ImageFileStorageService imageFileStorageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, ImageFileStorageService imageFileStorageService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageFileStorageService = imageFileStorageService;
    }


    @Override
    public User saveBlogAdmin(User user) {
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


    public User registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) {

        User user = new User();


        user.setEmail(email);
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));

        saveBlogAdmin(user);

        ImageFile imageFile = imageFileStorageService.storeImageFile(file, user);
        return user;
    }

}
