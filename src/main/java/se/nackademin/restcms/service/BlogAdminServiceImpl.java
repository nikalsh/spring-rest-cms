package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.security.UserDetailsImpl;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {

    @Autowired
    BlogAdminRepository blogAdminRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public BlogAdmin saveBlogAdmin(BlogAdmin blogAdmin) {

        if (blogAdmin.getRole().isEmpty()) {
            blogAdmin.addRole(authorityRepository.getOne(1));
        }
        blogAdmin.setPassword(passwordEncoder.encode(blogAdmin.getPassword()));

        return blogAdminRepository.save(blogAdmin);
    }

    @Override
    public BlogAdmin getCurrentBlogAdmin() {

        //get the current authenticated user
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogAdmin blogAdmin = user.getUser();

        return blogAdmin;
    }
}
