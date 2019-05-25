package se.nackademin.restcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.BlogAdmin;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BlogAdminRepository blogAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        BlogAdmin blogAdmin = blogAdminRepository.findByName(name);
        if (blogAdmin == null) {
            throw new UsernameNotFoundException(name);
        }
        return new MyUserPrincipal(blogAdmin);
    }
}

