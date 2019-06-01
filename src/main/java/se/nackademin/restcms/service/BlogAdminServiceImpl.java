package se.nackademin.restcms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogAdminRepository blogAdminRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public BlogAdmin saveBlogAdmin(BlogAdmin blogAdmin) {

        if (blogAdmin.getRole().isEmpty()) {
            blogAdmin.addRole(authorityRepository.findAll().get(0));
        }

        blogAdmin.setPassword(passwordEncoder.encode(blogAdmin.getPassword()));

        return blogAdminRepository.save(blogAdmin);
    }

    @Override
    public BlogAdmin getCurrentBlogAdmin() {
        //get the current authenticated user
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogAdmin blogAdmin = user.getUser();

        return blogAdmin;
    }

    @Override
    public BlogAdmin updatePassword(String password) {
        BlogAdmin blogAdmin = blogAdminRepository.findById(getCurrentBlogAdmin().getId()).get();
        blogAdmin.setPassword(passwordEncoder.encode(password));
        blogAdminRepository.save(blogAdmin);

        return getCurrentBlogAdmin();
    }
}
