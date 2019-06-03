package se.nackademin.restcms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.Security2.Entity.CustomUserDetail;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.*;
//import se.nackademin.restcms.security.UserDetailsImpl;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogAdminRepository blogAdminRepository;

    @Autowired
    AuthorityRepository authorityRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    ImageFileStorageService imageFileStorageService;


    @CrossOrigin(origins = "http://localhost:8081")
    @Override
    public BlogAdmin saveBlogAdmin(BlogAdmin blogAdmin) {

        if (blogAdmin.getRole().isEmpty()) {
            blogAdmin.addRole(authorityRepository.findAll().get(0));
        }

       // user.setPassword(passwordEncoder.encode(user.getPassword()));

        return blogAdminRepository.save(blogAdmin);
    }

    @Override
    public BlogAdmin getCurrentBlogAdmin() {
        //get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        CustomUserDetail user = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getUser();

    }

    @Override
    public BlogAdmin updatePassword(String password) {
        BlogAdmin blogAdmin = blogAdminRepository.findById(getCurrentBlogAdmin().getId()).get();
       // user.setPassword(passwordEncoder.encode(password));
        blogAdminRepository.save(blogAdmin);

        return getCurrentBlogAdmin();
    }


    public BlogAdmin registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) {

        BlogAdmin blogAdmin = new BlogAdmin();


        blogAdmin.setEmail(email);
        blogAdmin.setName(name);
      //  user.setPassword(passwordEncoder.encode(password));

        saveBlogAdmin(blogAdmin);

        ImageFile imageFile = imageFileStorageService.storeImageFile(file, blogAdmin);
        return blogAdmin;
    }

}
